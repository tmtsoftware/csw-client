package csw.client

object Main {

  def main(args: Array[String]): Unit = {
    ammonite
      .Main(
        predefCode = """
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
                |import csw.client.utils.Extensions._
                |import csw.client.CswHelpers._
                |import cswContext._
                |""".stripMargin
      )
      .run()

    CswHelpers.shutdown()

  }
}
