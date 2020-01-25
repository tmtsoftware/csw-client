lazy val `csw-shell` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.tmtsoftware.csw" %% "csw-framework" % "7fcdc09",
      "com.github.tmtsoftware.esw" %% "esw-ocs-impl"  % "3c0591d",
      "com.lihaoyi"                % "ammonite"       % "2.0.4" cross CrossVersion.full
    )
  )
