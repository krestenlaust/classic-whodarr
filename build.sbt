ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "classic-whodarr"
  )

libraryDependencies ++= Seq(
  "org.scalatest"    %% "scalatest" % "3.2.17" % "test",
  "com.lihaoyi"      %% "os-lib"    % "0.10.1",
  "com.typesafe"      % "config"    % "1.4.3",
  "net.lingala.zip4j" % "zip4j"     % "2.11.5"
)
