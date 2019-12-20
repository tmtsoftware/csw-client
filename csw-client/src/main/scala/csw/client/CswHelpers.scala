package csw.client

import akka.actor.typed.{ActorSystem, SpawnProtocol}
import csw.command.api.scaladsl.CommandService
import csw.command.client.CommandServiceFactory
import csw.framework.CswClientWiring
import csw.framework.models.CswContext
import csw.location.models.ComponentType.{Assembly, HCD}
import csw.location.models.Connection.AkkaConnection
import csw.location.models.{AkkaLocation, ComponentId, ComponentType}
import csw.prefix.models.Prefix
import utils.Extensions.FutureExt

class CswHelpers(cswClientWiring: CswClientWiring) {

  import cswClientWiring._
  lazy val cswContext: CswContext = cswClientWiring.cswContext
  import cswContext._
  implicit lazy val typedSystem: ActorSystem[SpawnProtocol.Command] =
    cswClientWiring.wiring.actorSystem

  def assemblyCommandService(prefix: String): CommandService =
    createCommandService(getAkkaLocation(prefix, Assembly))

  def hcdCommandService(prefix: String): CommandService =
    createCommandService(getAkkaLocation(prefix, HCD))

  private def getAkkaLocation(prefix: String,
                              cType: ComponentType): AkkaLocation = {
    val maybeLocation = locationService
      .resolve(AkkaConnection(ComponentId(Prefix(prefix), cType)), timeout)
      .await()
    maybeLocation.getOrElse(
      throw new RuntimeException(
        s"Location not found for component: name:[$prefix] type:[${cType.name}]"
      )
    )
  }

  private def createCommandService: AkkaLocation => CommandService =
    CommandServiceFactory.make
}
