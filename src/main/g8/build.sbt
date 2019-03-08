import sbt.Keys._
import ch.tamedia.sbt.Settings
import sbt.Keys._

lazy val root = (
  Project("$name;format="normalize"$", file("."))
    enablePlugins(JavaAppPackaging, BuildInfoPlugin, GitVersioning)
settings(Settings.root ++ LocalDependencies.dependencies ++ Release.settings: _*)
  )

name := "$name$"

// BuildInfo generation
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)
buildInfoPackage := "$package$"
