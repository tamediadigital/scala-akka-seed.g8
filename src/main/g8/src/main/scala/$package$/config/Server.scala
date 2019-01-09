package $package$.service.config

import akka.http.scaladsl.Http
import $package$.service.main.{Core, CoreActors}
import $package$.service.services.config.ApiConfig

/**
  * Server configuration
  */
trait Server {
  this: ApiConfig with CoreActors with Core =>

  import $package$.service.config.AppConfig._

  Http().bindAndHandle(apiRoutes, host, port)

}
