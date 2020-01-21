package csw.framework

import csw.command.client.models.framework.{ComponentInfo, LocationServiceUsage}
import csw.framework.internal.wiring.{CswFrameworkSystem, FrameworkWiring}
import csw.framework.models.CswContext
import csw.location.models.ComponentType
import csw.prefix.models.Prefix
import shell.utils.Extensions.FutureExt

class ShellWiring {
  lazy val wiring = new FrameworkWiring
  import wiring._
  import actorRuntime._

  private implicit lazy val cswFrameworkSystem: CswFrameworkSystem = new CswFrameworkSystem(typedSystem)

  lazy val cswContext: CswContext =
    CswContext
      .make(
        locationService,
        eventServiceFactory,
        alarmServiceFactory,
        // dummy component info, it is not used by csw-shell
        ComponentInfo(
          Prefix("csw.shell"),
          ComponentType.Service,
          "",
          LocationServiceUsage.DoNotRegister
        )
      )
      .await()
}
