addSbtPlugin("org.scalameta"                     % "sbt-scalafmt"               % "2.0.0")
addSbtPlugin("com.typesafe.sbt"                  % "sbt-native-packager"        % "1.3.22")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0")
classpathTypes += "maven-plugin"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  //"-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Xfuture"
)
