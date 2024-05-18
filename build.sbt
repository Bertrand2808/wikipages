name := "Examen Scala"

This / scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.2",
  "com.github.scopt" %% "scopt" % "4.1.0",
  "org.playframework" %% "play-json" % "3.0.3"
)
