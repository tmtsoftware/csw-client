interp.repositories() ++= Seq(coursier.Repositories.jitpack)

@

import $ivy.`com.github.tmtsoftware.csw::csw-framework:0.7.0`

import akka.util.Timeout
import csw.command.api.scaladsl.CommandService
import csw.command.client.CommandServiceFactory
import csw.framework.internal.wiring.FrameworkWiring
import csw.location.api.models.ComponentType.{Assembly, HCD}
import csw.location.api.models.Connection.AkkaConnection
import csw.location.api.models.{AkkaLocation, ComponentId, ComponentType}

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.concurrent.{Await, Future}

implicit class FutureExt[T](val future: Future[T]) {
  def await(duration: FiniteDuration = 20.seconds): T = Await.result(future, duration)
  def get: T = future.await()
}

implicit val timeout: Timeout = Timeout(10.seconds)

val wiring = new FrameworkWiring
import wiring._
import actorRuntime._

def assemblyCommandService(assemblyName: String): CommandService = createCommandService(getAkkaLocation(assemblyName, Assembly))
def hcdCommandService(hcdName: String): CommandService = createCommandService(getAkkaLocation(hcdName, HCD))
def getAkkaLocation(name: String, cType: ComponentType): AkkaLocation = {
  val maybeLocation = locationService.resolve(AkkaConnection(ComponentId(name, cType)), timeout.duration).await()
  maybeLocation.getOrElse(
    throw new RuntimeException(
      s"Location not found for component: name:[$name] type:[${cType.name}]"
    )
  )
}

def createCommandService: AkkaLocation ⇒ CommandService = CommandServiceFactory.make

import scala.concurrent.duration.Duration
import akka.util.Timeout
import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, Future}
import csw.params.core.generics.KeyType._
import csw.params.events.SystemEvent
import csw.params.events.EventName
import csw.params.events.EventKey
import csw.params.commands._
import csw.params.core.models._

System.setProperty("INTERFACE_NAME", "en0")

locationService.list
