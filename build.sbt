name := """mysite"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
    jdbc,
    anorm,
    cache,
    ws,
    "com.typesafe.akka" %% "akka-actor" % "2.3.11",
    "com.github.fdimuccio" %% "play2-sockjs" % "0.3.1",
    "org.atmosphere" % "atmosphere-play" % "2.1.2"
)
