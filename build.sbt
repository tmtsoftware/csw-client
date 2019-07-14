
lazy val `csw-client` = project
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "0.7.0",
      "com.lihaoyi" % "ammonite" % "1.6.8" cross CrossVersion.full
    )
  )