name := "ow-scala"
organization := "uk.camsw"
scalaVersion := "2.12.4"
version := "1.0.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers ++= Seq("jBCrypt Repository" at "http://repo1.maven.org/maven2/org/")

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/scalatest-report")

libraryDependencies += "org.pegdown" % "pegdown" % "1.0.2" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.6" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test


