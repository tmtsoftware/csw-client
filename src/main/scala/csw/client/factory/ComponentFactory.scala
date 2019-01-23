package csw.client.factory

import akka.actor.typed.scaladsl.adapter.UntypedActorSystemOps
import akka.actor.{typed, ActorSystem}
import csw.command.api.scaladsl.CommandService
import csw.command.client._
import csw.location.api.exceptions.RegistrationListingFailed
import csw.location.api.models.ComponentType.{Assembly, HCD}
import csw.location.api.models.Connection.AkkaConnection
import csw.location.api.models.{AkkaLocation, ComponentId, ComponentType, Location}
import csw.location.api.scaladsl.LocationService

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class ComponentFactory(locationService: LocationService)(implicit system: ActorSystem) {

  import system.dispatcher
  implicit val typedSystem: typed.ActorSystem[Nothing] = system.toTyped

  def assemblyCommandService(assemblyName: String): Future[CommandService] = creatCommandService(resolve(assemblyName, Assembly))

  def hcdCommandService(hcdName: String): Future[CommandService] = creatCommandService(resolve(hcdName, HCD))

  private def resolve(name: String, cType: ComponentType): Future[Option[AkkaLocation]] =
    locationService.resolve(AkkaConnection(ComponentId(name, cType)), 10.seconds)

  private def creatCommandService: Future[Option[AkkaLocation]] ⇒ Future[CommandService] = _.map {
    case Some(location) ⇒ CommandServiceFactory.make(location)
    case None           => throw RegistrationListingFailed
  }

  def listAssemblies(): Future[List[Location]] = locationService.list(ComponentType.Assembly)
  def listHcd(): Future[List[Location]]        = locationService.list(ComponentType.HCD)
}
