lazy val `csw-client` = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    libraryDependencies ++= Dependencies.CswClient
  )