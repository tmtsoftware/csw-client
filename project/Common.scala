import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt.Keys._
import sbt.plugins.JvmPlugin
import sbt.{url, _}

object Common extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = JvmPlugin

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    organization := "org.tmt",
    organizationName := "TMT",
    scalaVersion := "2.12.8",
    organizationHomepage := Some(url("http://www.tmt.org")),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Xfuture"
    ),
    javacOptions in (Compile, doc) ++= Seq("-Xdoclint:none"),
    resolvers += "jitpack" at "https://jitpack.io",
    version := "0.1-SNAPSHOT",
    autoCompilerPlugins := true,
    if (formatOnCompile) scalafmtOnCompile := true else scalafmtOnCompile := false
  )

  private def formatOnCompile = sys.props.get("format.on.compile") match {
    case Some("false") ⇒ false
    case _             ⇒ true
  }
}
