name := "U2VideoApiServer"
 
version := "1.0" 
      
lazy val `u2videoapiserver` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
// https://mvnrepository.com/artifact/org.ahocorasick/ahocorasick
libraryDependencies += "org.ahocorasick" % "ahocorasick" % "0.4.0"
// https://mvnrepository.com/artifact/com.sksamuel.elastic4s/elastic4s-http
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-http" % "6.5.0"
// https://mvnrepository.com/artifact/log4j/log4j
libraryDependencies += "log4j" % "log4j" % "1.2.17"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      