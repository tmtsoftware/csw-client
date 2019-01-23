package csw.client.factory

import akka.actor.typed.scaladsl.adapter.UntypedActorSystemOps
import akka.actor.{typed, ActorSystem}
import csw.command.api.scaladsl.CommandService
import csw.command.client._
import csw.location.api.models.{AkkaLocation, ComponentType}

import scala.concurrent.Future

class ComponentFactory(locationService: LocationServiceWrapper)(implicit system: ActorSystem) {

  implicit val typedSystem: typed.ActorSystem[Nothing] = system.toTyped

  def assemblyCommandService(assemblyName: String): Future[CommandService] = {
    locationService.resolve(assemblyName, ComponentType.Assembly)(akkaLocation => CommandServiceFactory.make(akkaLocation))
  }

  def hcdCommandService(hcdName: String): Future[CommandService] = {
    locationService.resolve(hcdName, ComponentType.HCD)(akkaLocation => CommandServiceFactory.make(akkaLocation))
  }

  def assemblyLocation(assemblyName: String): Future[AkkaLocation] = {
    locationService.resolve(assemblyName, ComponentType.Assembly)(identity)
  }
}
