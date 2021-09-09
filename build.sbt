ThisBuild / scalaVersion := "3.0.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "jacob"
ThisBuild / organizationName := "jacob"

val catsV = "2.6.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "budget",
    libraryDependencies ++= List(
      "org.typelevel" %% "cats-core" % catsV
    ),
  )
