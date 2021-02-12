package $package$.main

import scala.concurrent.ExecutionContext

/**
  * Core is type containing the ``system: ActorSystem`` member. This enables usage in apps as well as in our tests.
  */
trait Core {
  implicit def system: ActorSystem

  implicit def matFromSystem(implicit provider: ClassicActorSystemProvider): Materializer =
    SystemMaterializer(provider.classicSystem).materializer

  implicit val ex: ExecutionContext = system.dispatcher
}


/**
  * This trait implements ``Core`` by starting the required ``ActorSystem`` and registering the
  * termination handler to stop the system when the JVM exits.
  */
trait BootedCore extends Core {

  /**
    * Construct the ActorSystem we will use in our application
    */
  implicit lazy val system = ActorSystem("service-seed")

  /**
    * Ensure that the constructed ActorSystem is terminated when the JVM shuts down
    */
  sys.addShutdownHook(system.terminate())

}

/**
  * This trait contains the actors that make up application; it can be mixed in with
  * ``BootedCore`` for running code or ``TestKit`` for unit and integration tests.
  */
trait CoreActors {
  this: Core =>


  val serviceActor = system.actorOf(ServiceActor.props(config))


}
