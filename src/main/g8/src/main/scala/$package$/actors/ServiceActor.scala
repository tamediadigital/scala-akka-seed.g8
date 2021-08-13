package $package$.actors

import com.typesafe.config.Config

import scala.concurrent.{ExecutionContext, Future}

object ServiceActor {
  def props(config: Config)(implicit ec: ExecutionContext) = Props(new ServiceActor(config))
}

class ServiceActor(config: Config)(implicit ec: ExecutionContext) extends Actor with ActorLogging with DefaultJsonProtocol with SprayJsonSupport {

  implicit val as: ActorSystem = context.system
  implicit def matFromSystem(implicit provider: ClassicActorSystemProvider): Materializer =
    SystemMaterializer(provider.classicSystem).materializer


  override def receive: Receive = {
    // Implement your logic here
    case r: Request => {
      val senderInstance = sender()
      Future.successful("OK") pipeTo senderInstance
    }


    case _ => log.warning("Unhandled message")
  }

}

