import sbt._

object Libs {
  val ScalaVersion = "2.12.8"
}

object CSW {
  val Version = "0.1-SNAPSHOT"

  val `csw-framework` = "com.github.tmtsoftware.csw" %% "csw-framework" % Version
}

object Ammonite {
  val Version    = "1.5.0"
  val `ammonite` = "com.lihaoyi" % "ammonite" % Version cross CrossVersion.full
}
