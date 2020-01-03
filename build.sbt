lazy val `csw-client` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "v1.0.0",
      "com.github.tmtsoftware.esw" %% "esw-ocs-impl"  % "f2d7818",
      "com.lihaoyi"                % "ammonite"       % "2.0.1" cross CrossVersion.full
    )
  )
