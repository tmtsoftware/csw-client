
lazy val `csw-client` = project
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "1.0.0",
      "com.lihaoyi" % "ammonite" % "1.9.4" cross CrossVersion.full
    )
  )
