package $package$.service.config

import com.typesafe.config.ConfigFactory

/**
  * Class that contains all application and server configurations
  */
object AppConfig {

  // load application configurations
  implicit val config = ConfigFactory.load("application.conf")

  val host = config.getString("http.interface")
  val port = config.getInt("http.port")

}
