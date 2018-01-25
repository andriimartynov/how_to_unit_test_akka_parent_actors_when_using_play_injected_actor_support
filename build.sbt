name := "how_to_unit_test_akka_parent_actors_when_using_play_injected_actor_support"
 
version := "1.0" 
      
lazy val `how_to_unit_test_akka_parent_actors_when_using_play_injected_actor_support` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  guice,
  "org.scalactic" %% "scalactic" % "3.0.4" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.8" % Test
)