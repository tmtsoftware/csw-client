package esw

import akka.actor.typed.{ActorSystem, SpawnProtocol}
import akka.util.Timeout
import shell.utils.Extensions.FutureExt
import shell.utils.Timeouts
import csw.command.api.scaladsl.CommandService
import csw.command.client.CommandServiceFactory
import csw.command.client.extensions.AkkaLocationExt.RichAkkaLocation
import csw.framework.ShellWiring
import csw.location.models.ComponentType.{Assembly, HCD}
import csw.prefix.models.{Prefix, Subsystem}
import esw.ocs.impl.SequencerActorProxy
import esw.ocs.impl.internal.LocationServiceUtil

class CommandServiceDsl(val shellWiring: ShellWiring) {
  implicit lazy val typedSystem: ActorSystem[SpawnProtocol.Command] = shellWiring.wiring.actorSystem

  import typedSystem.executionContext

  private implicit val implicitTimeout: Timeout = Timeouts.defaultTimeout
  private val locationUtil: LocationServiceUtil = new LocationServiceUtil(shellWiring.cswContext.locationService)

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