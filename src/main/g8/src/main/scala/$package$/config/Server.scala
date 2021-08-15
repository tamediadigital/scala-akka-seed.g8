package $package$.config

import akka.http.scaladsl.Http
import $package$.main.{Core, CoreActors}
import $package$.services.config.ApiConfig

/**
  * Server configuration
  */
trait Server {
  this: ApiConfig with CoreActors with Core =>

  import $package$.config.AppConfig._

  Http().newServerAt(host, port).bind(apiRoutes)

}
