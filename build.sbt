lazy val `csw-shell` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "1.1.0-RC1",
      "com.github.tmtsoftware.esw" %% "esw-ocs-impl"  % "3c0591d",
      "com.lihaoyi"                % "ammonite"       % "2.0.1" cross CrossVersion.full
    )
  )
