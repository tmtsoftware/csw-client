inThisBuild(
  Seq(
    organization := "com.github.tmtsoftware.csw-shell",
    organizationName := "TMT Org",
    scalaVersion := "2.13.2",
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
    resolvers += "jitpack" at "https://jitpack.io",
    resolvers += Resolver.bintrayRepo("lonelyplanet", "maven"),
    version := "0.1.0-SNAPSHOT"
  )
)

lazy val `csw-shell-root` = project.in(file(".")).aggregate(`csw-shell`)

lazy val `csw-shell` = project
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "65c4199",
      "com.github.tmtsoftware.esw" %% "esw-ocs-impl"  % "a202a1e",
      "com.lihaoyi"                % "ammonite"       % "2.1.4" cross CrossVersion.full
    )
  )
