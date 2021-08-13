package $package$.services.config

import akka.http.scaladsl.server.RouteConcatenation
import ch.tamedia.http.CorsSupport
import $package$.service.errors.ErrorHandler
import $package$.main.{Core, CoreActors}


/**
  * The REST API layer. It exposes the REST services<br/>
  * Notice that it requires to be mixed in with ``core.CoreActors``, which provides access
  * to the top-level actors that make up the system.
  */
trait ApiConfig extends RouteConcatenation with CorsSupport with SimpleService {
  this: CoreActors with Core =>

  private implicit val _ = system.dispatcher
  //  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))

  implicit val routeExceptionHandler = ErrorHandler.exceptionHandler

  val apiRoutes = {
    corsHandler(routes(serviceActor))
  }
}
