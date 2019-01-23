package csw.client.utils

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{DurationInt, FiniteDuration}

object Extensions {

  implicit class FutureExt[T](val future: Future[T]) extends AnyVal {
    def await(duration: FiniteDuration = 20.seconds): T = Await.result(future, duration)
    def get: T                                          = future.await()
  }

}
