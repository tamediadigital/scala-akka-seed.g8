

object Settings {

  lazy val root =
    Common.settings ++
      Dependencies.resolvers ++
      Dependencies.test ++
      Testing.settings ++
      Publish.publishers ++
      Publish.settings ++
      Release.settings
}