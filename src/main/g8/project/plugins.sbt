
resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  "Tamedia Maven Repository" at "https://nexus3.dev.tda.link/repository/maven-releases",
  "Tamedia Maven Repository Snapshots" at "https://nexus3.dev.tda.link/repository/maven-snapshots"
)

addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.8")

// https://github.com/sbt/sbt-native-packager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.6")

// generates scala file about guild
// https://github.com/sbt/sbt-buildinfo
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.9.0")

// allows usage of git in sbt
// https://github.com/sbt/sbt-git
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")

// sbt automatic releases
// https://github.com/sbt/sbt-release
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.9")

// java agent for kamon
addSbtPlugin("com.lightbend.sbt" % "sbt-javaagent" % "0.1.4")

// multi jvm
addSbtPlugin("com.typesafe.sbt" % "sbt-multi-jvm" % "0.4.0")


addSbtPlugin("ch.tamedia" % "sbt-configurator" % "1.0.4")

