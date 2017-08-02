name := "AkkaAssignment"

version := "1.0"

scalaVersion := "2.12.3"

val akkaVersion = "2.4.17"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1"
)
        