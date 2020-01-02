package esw.client

import akka.actor.typed.{ActorSystem, SpawnProtocol}
import akka.util.Timeout
import csw.command.client.extensions.AkkaLocationExt.RichAkkaLocation
import csw.framework.CswClientWiring
import csw.framework.models.CswContext
import csw.prefix.models.Subsystem
import esw.ocs.impl.SequencerActorProxy
import esw.ocs.impl.internal.LocationServiceUtil
import utils.Extensions.FutureExt
import utils.Timeouts

class EswHelpers(cswClientWiring: CswClientWiring) {
  private lazy val cswContext: CswContext = cswClientWiring.cswContext
  import cswContext._
  implicit lazy val typedSystem: ActorSystem[SpawnProtocol.Command] =
    cswClientWiring.wiring.actorSystem

  import typedSystem.executionContext

  private implicit val implicitTimeout: Timeout = Timeouts.defaultTimeout
  private val locationUtil: LocationServiceUtil = new LocationServiceUtil(
    locationService
  )

  def sequencerCommandService(subsystem: Subsystem, observingMode: String): SequencerActorProxy = {
    locationUtil
      .resolveSequencer(subsystem, observingMode)
      .map(akkaLocation => new SequencerActorProxy(akkaLocation.sequencerRef))
      .await()
  }
}
