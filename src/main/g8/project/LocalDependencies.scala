import ch.tamedia.sbt.Dependencies

object LocalDependencies {
  val dependencies =
    Dependencies.depends(
      Dependencies.tamediaCommons ++
        Dependencies.jackson ++
        Dependencies.typesafe ++
        Dependencies.tamediaKafka ++
        Dependencies.tamediaHttp ++
        Seq(
          "org.scalactic" %% "scalactic" % "3.2.9",
          "org.scalatest" % "scalatest_2.12" % "3.2.9" % Test
        )
    )
}
