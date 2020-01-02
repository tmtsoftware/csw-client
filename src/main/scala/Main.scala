import csw.client.CswHelpers
import csw.framework.ClientWiring
import esw.client.CommandServiceDsl

object Main {

  def main(args: Array[String]): Unit = {
    val clientWiring = new ClientWiring
    ammonite
      .Main(
        predefCode = """
                |import akka.util.Timeout
                |import scala.concurrent.duration.{Duration, DurationDouble}
                |import scala.concurrent.{Await, Future}
                |import csw.params.core.generics.KeyType._
                |import csw.params.events._
                |import csw.params.commands._
                |import csw.params.core.models._
                |import csw.prefix.models.Subsystem._
                |import csw.prefix.models.Prefix
                |import utils.Extensions._
                |import utils.Timeouts._
                |import commandService._
                |import commandService.clientWiring.cswContext._
                |""".stripMargin
      )
      .run(
        "commandService" -> new CommandServiceDsl(clientWiring)
      )
  }
}
