lazy val `csw-shell` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "2.0.0-RC3",
      "com.github.tmtsoftware.esw" %% "esw-ocs-impl"  % "0.1.0-RC3",
      "com.lihaoyi"                % "ammonite"       % "2.0.1" cross CrossVersion.full
    )
  )
