
lazy val `csw-client` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "f7e6357",
      "com.lihaoyi" % "ammonite" % "1.6.7" cross CrossVersion.full
    )
  )