import $package$.service.config.Server
import $package$.service.main.{BootedCore, CoreActors}
import $package$.service.services.config.ApiConfig

/**
  * Entry point of the app
  * BootCore - place where all actors system is initialized
  * CoreActors - place where actors are intialized
  * ApiConfig - place where all routes are intialized
  * Server - place where server is configured and started
  */
object MainApplication extends App with BootedCore with CoreActors with ApiConfig with Server
