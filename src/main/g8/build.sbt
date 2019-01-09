import sbt.Keys._

lazy val root = (
  Project("$name;format="normalize"$", file("."))
    enablePlugins(JavaAppPackaging, BuildInfoPlugin, GitVersioning)
    settings (Settings.root: _*)
  )

// BuildInfo generation
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)
buildInfoPackage := "$package$"
