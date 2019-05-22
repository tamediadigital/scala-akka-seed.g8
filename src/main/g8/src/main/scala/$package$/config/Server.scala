package $package$.service.config

import akka.http.scaladsl.Http
import $package$.main.{Core, CoreActors}
import $package$.services.config.ApiConfig

/**
  * Server configuration
  */
trait Server {
  this: ApiConfig with CoreActors with Core =>

  import $package$.config.AppConfig._

  Http().bindAndHandle(apiRoutes, host, port)

}
