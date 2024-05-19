ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "classic-whodarr"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % "test",
  "com.typesafe"   % "config"    % "1.4.3"
)
