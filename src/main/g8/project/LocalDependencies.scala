import ch.tamedia.sbt.Dependencies
import sbt._

object LocalDependencies {
  val dependencies =
    Dependencies.depends(
      Dependencies.tamediaCommons ++
        Dependencies.jackson ++
        Dependencies.typesafe ++
        Dependencies.tamediaKafka ++
        Dependencies.tamediaHttp ++
        Dependencies.txAlertina ++
        Seq(
          "org.scalactic" %% "scalactic" % "3.2.9",
          "org.scalatest" % "scalatest_2.12" % "3.2.9" % Test
        )
    )
}
