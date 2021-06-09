name := """play-scala-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"
lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play"      %% "play-slick"            % "5.0.0"
libraryDependencies += "com.typesafe.play"      %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "com.typesafe.slick"     %% "slick-codegen"         % "3.3.2"
libraryDependencies += "com.typesafe"            % "config"                % "1.4.0"
libraryDependencies += "mysql"                   % "mysql-connector-java"  % "6.0.6"

// add code generation task
lazy val slickCodeGen = taskKey[Unit]("execute Slick CodeGen")
slickCodeGen         := (runMain in Compile).toTask(" tasks.SlickCodeGen").value
