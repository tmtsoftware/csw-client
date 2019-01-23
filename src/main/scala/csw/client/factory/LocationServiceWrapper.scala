package csw.client.factory

import akka.actor.ActorSystem
import csw.location.api.models.Connection.AkkaConnection
import csw.location.api.models._
import csw.location.api.scaladsl.LocationService

import scala.concurrent.duration.DurationDouble
import scala.concurrent.{ExecutionContext, Future}

class LocationServiceWrapper(locationService: LocationService, system: ActorSystem)(implicit ec: ExecutionContext) {

  def resolve[T](componentName: String, componentType: ComponentType)(f: AkkaLocation => T): Future[T] =
    locationService
      .resolve(AkkaConnection(ComponentId(componentName, componentType)), 5.seconds)
      .map {
        case Some(akkaLocation) => f(akkaLocation)
        case None               => throw new IllegalArgumentException(s"Could not find component - $componentName of type - $componentType")
      }

  def listAssemblies(): Future[List[Location]] = locationService.list(ComponentType.Assembly)
  def listHcd(): Future[List[Location]]        = locationService.list(ComponentType.HCD)
}
