package esw.client

import akka.actor.typed.{ActorSystem, SpawnProtocol}
import akka.util.Timeout
import csw.command.api.scaladsl.CommandService
import csw.command.client.CommandServiceFactory
import csw.command.client.extensions.AkkaLocationExt.RichAkkaLocation
import csw.framework.ClientWiring
import csw.location.models.ComponentType.{Assembly, HCD}
import csw.prefix.models.{Prefix, Subsystem}
import esw.ocs.impl.SequencerActorProxy
import esw.ocs.impl.internal.LocationServiceUtil
import utils.Extensions.FutureExt
import utils.Timeouts

class CommandServiceDsl(val clientWiring: ClientWiring) {
  implicit lazy val typedSystem: ActorSystem[SpawnProtocol.Command] = clientWiring.wiring.actorSystem

  import typedSystem.executionContext

  private implicit val implicitTimeout: Timeout = Timeouts.defaultTimeout
  private val locationUtil: LocationServiceUtil = new LocationServiceUtil(clientWiring.cswContext.locationService)

  def sequencerCommandService(subsystem: Subsystem, observingMode: String): SequencerActorProxy =
    locationUtil
      .resolveSequencer(subsystem, observingMode)
      .map(akkaLocation => new SequencerActorProxy(akkaLocation.sequencerRef))
      .await()

  def assemblyCommandService(prefix: String): CommandService =
    CommandServiceFactory.make(locationUtil.resolveAkkaLocation(Prefix(prefix), Assembly).await())

  def hcdCommandService(prefix: String): CommandService =
    CommandServiceFactory.make(locationUtil.resolveAkkaLocation(Prefix(prefix), HCD).await())
}
