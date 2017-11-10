name := "simplePlay"

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
//  "postgresql" % "postgresql" % "9.1-901.jdbc4",
//  "com.typesafe.slick" %% "slick" % "3.0.0",
//  "org.slf4j" % "slf4j-nop" % "1.6.4",
//  "com.typesafe.slick" %% "slick-codegen" % "3.0.0"
//  "com.typesafe.slick" %% "slick" % "3.0.0-RC1",
//"org.slf4j" % "slf4j-nop" % "1.6.4",
//"postgresql" % "postgresql" % "9.1-901.jdbc4"
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4"
)
