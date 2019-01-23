package csw.client

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.Timeout
import csw.client.factory.ComponentFactory
import csw.event.api.scaladsl.EventService
import csw.framework.internal.wiring.FrameworkWiring
import csw.location.client.ActorSystemFactory

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationDouble

class Wiring() {
  implicit lazy val timeout: Timeout = Timeout(5.seconds)

  lazy implicit val frameworkSystem: ActorSystem       = ActorSystemFactory.remote("framework")
  lazy implicit val materializer: Materializer         = ActorMaterializer()
  lazy implicit val executionContext: ExecutionContext = frameworkSystem.dispatcher

  val frameworkWiring: FrameworkWiring = FrameworkWiring.make(frameworkSystem)
  import frameworkWiring._

  lazy val eventService: EventService = eventServiceFactory.make(locationService)
  lazy val componentFactory           = new ComponentFactory(locationService)
}
