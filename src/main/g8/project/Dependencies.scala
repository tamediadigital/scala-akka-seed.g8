import sbt.Keys._
import sbt._

object Dependencies {

  private val akkaVersion = "$akka_version$"
  private val akkaHttpVersion = "$akka_http_version$"
  private val scalaTestVersion = "$scala_test_version$"
  private val jacksonVersion = "$jackson_version$"
  private val log4jVersion = "$log4j_version$"

  private val typesafe = Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion
  )

  private val tests = "test"

  private val scalatic = Seq("org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalactic" %% "scalactic" % scalaTestVersion % Test)

  private val scalatest = Seq("org.scalatest" % "scalatest_2.12" % scalaTestVersion % Test)

  private val jackson = Seq("com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion)


  private val tamedia = Seq("ch.tamedia" % "blackbeard-commons" % "0.1.2",
                            "ch.tamedia" % "blackbeard-http" % "0.1.4")

  private val log4j = Seq("log4j" % "log4j" % log4jVersion)
  // Projects
  private val libs = Seq(typesafe, jackson, log4j, tamedia).flatten

  private def depends(modules: Seq[ModuleID]): Seq[Setting[_]] = Seq(libraryDependencies ++= modules)

  val core = depends(libs)

  val test = depends(libs ++ scalatest ++ scalatic)

  val repositories = Seq(Resolver.bintrayRepo("cakesolutions", "maven"),
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Artima Maven Repository" at "http://repo.artima.com/releases",
    "Private Maven Repository" at "$nexus$/repository/maven-releases",
    "Private Maven Snapshot" at "$nexus$/repository/maven-snapshots")

  object Tamedia {

    private val nexus = "$nexus$"

    val snapshots = "snapshots" at nexus + "repository/maven-snapshots"
    val releases = "releases" at nexus +  "repository/maven-releases"

    val resolvers = Seq(snapshots, releases)
  }

  // Resolvers
  val resolvers = Keys.resolvers ++= repositories

}