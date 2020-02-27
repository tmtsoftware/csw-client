import sbt.Keys._
import sbt.plugins.JvmPlugin
import sbt.{url, _}

object Common extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = JvmPlugin

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    organization := "com.github.tmtsoftware.csw-shell",
    organizationName := "TMT Org",
    scalaVersion := "2.13.1",
    organizationHomepage := Some(url("http://www.tmt.org")),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-Ywarn-dead-code"
//      "-Xfuture"
    ),
    javacOptions in (Compile, doc) ++= Seq("-Xdoclint:none"),
    resolvers += "jitpack" at "https://jitpack.io",
    version := "0.1.0-SNAPSHOT",
    autoCompilerPlugins := true
  )

}
