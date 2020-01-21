interp.repositories() ++= Seq(coursier.Repositories.jitpack)

@

import akka.util.Timeout
import csw.command.api.scaladsl.CommandService
import csw.command.client.CommandServiceFactory
import csw.framework.internal.wiring.FrameworkWiring
import csw.location.models.ComponentType.{Assembly, HCD}
import csw.location.models.Connection.AkkaConnection
import csw.location.models.{AkkaLocation, ComponentId, ComponentType}
import csw.prefix.models.Prefix
import shell.utils.Timeouts

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.{Await, Future}

implicit class FutureExt[T](val future: Future[T]) {
  def await(duration: FiniteDuration = Timeouts.defaultDuration): T = Await.result(future, duration)
  def get: T = future.await()
}

implicit val timeout: Timeout = Timeouts.defaultTimeout

val wiring = new FrameworkWiring
import wiring._
import actorRuntime._

def assemblyCommandService(assemblyName: String): CommandService = createCommandService(getAkkaLocation(assemblyName, Assembly))
def hcdCommandService(hcdName: String): CommandService = createCommandService(getAkkaLocation(hcdName, HCD))
def getAkkaLocation(prefix: String, cType: ComponentType): AkkaLocation = {
  val maybeLocation = locationService.resolve(AkkaConnection(ComponentId(Prefix(prefix), cType)), timeout.duration).await()
  maybeLocation.getOrElse(
    throw new RuntimeException(
      s"Location not found for component: name:[$prefix] type:[${cType.name}]"
    )
  )
}

def createCommandService: AkkaLocation ⇒ CommandService = CommandServiceFactory.make

System.setProperty("INTERFACE_NAME", "en0")

locationService.list
