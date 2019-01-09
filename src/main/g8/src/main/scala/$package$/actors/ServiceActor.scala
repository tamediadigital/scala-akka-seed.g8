package $package$.service.actors

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import $package$.models.Request
import com.typesafe.config.Config
import spray.json.DefaultJsonProtocol

import scala.concurrent.{ExecutionContext, Future}

object ServiceActor {
  def props(config: Config)(implicit ec: ExecutionContext) = Props(new ServiceActor(config))
}

class ServiceActor(config: Config)(implicit ec: ExecutionContext) extends Actor with ActorLogging with DefaultJsonProtocol with SprayJsonSupport {

  implicit val as: ActorSystem = context.system
  implicit val materializer = ActorMaterializer()


  override def receive: Receive = {
    // Implement your logic here
    case r: Request => {
      val senderInstance = sender()
      Future.successful("OK") pipeTo senderInstance
    }


    case _ => log.warning("Unhandled message")
  }

}

