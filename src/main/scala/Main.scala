import csw.client.CswHelpers
import csw.framework.CswClientWiring
import esw.client.EswHelpers

object Main {

  def main(args: Array[String]): Unit = {
    val clientWiring = new CswClientWiring
    ammonite
      .Main(predefCode = """
                |import scala.concurrent.duration.Duration
                |import akka.util.Timeout
                |import scala.concurrent.duration.DurationDouble
                |import scala.concurrent.{Await, Future}
                |import csw.params.core.generics.KeyType._
                |import csw.params.events.SystemEvent
                |import csw.params.events.EventName
                |import csw.params.events.EventKey
                |import csw.params.commands._
                |import csw.params.core.models._
                |import utils.Extensions._
                |import utils.Timeouts._
                |import csw.prefix.models.Subsystem._
                |import csw.prefix.models.Prefix
                |import cswHelpers._
                |import eswHelpers._
                |import cswContext._
                |""".stripMargin)
      .run(
        "cswHelpers" -> new CswHelpers(clientWiring),
        "eswHelpers" -> new EswHelpers(clientWiring)
      )
  }
}
