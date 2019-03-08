import ch.tamedia.sbt.Dependencies

object LocalDependencies {
  val dependencies =
    Dependencies.depends(
      Dependencies.tamediaCommons ++
        Dependencies.jackson ++
        Dependencies.typesafe ++
        Dependencies.scalaKafka ++
        Dependencies.tamediaKafka ++
        Dependencies.tamediaHttp
    )
}