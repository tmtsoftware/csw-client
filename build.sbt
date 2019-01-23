
lazy val `csw-client` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "0.1-SNAPSHOT",
      "com.lihaoyi" % "ammonite" % "1.5.0" cross CrossVersion.full
    )
  )