import sbt.Keys._

object Common {

  val compilerOptions = Seq(
    "-encoding", "UTF-8", // force encoding to UTF-8
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-feature", // Emit warning and location for usages of features that should be imported explicitly.
    "-Xfuture", // Turn on future language features.
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-language:postfixOps", // Allow postfix operator
    "-Xfatal-warnings", // Fail the compilation if there are any warnings
    "-Xlint:-missing-interpolator", // Enable recommended additional warnings.
    "-Yno-adapted-args", // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
    "-Ywarn-dead-code" // Warn when dead code is identified.
  )

  val settings = Seq(
    organization := "$package$",
    name := "$name$",
    scalaVersion := "2.12.7",
    scalacOptions ++= compilerOptions,
    incOptions := incOptions.value,
    javaOptions ++= Seq()
  )
}