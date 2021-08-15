package ch.tx.actors

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.pattern.pipe
import $package$.models.{Item, Request, Response}
import com.typesafe.config.Config
import spray.json.DefaultJsonProtocol

import scala.concurrent.{ExecutionContext, Future}

object ServiceActor {
  def props(config: Config)(implicit ec: ExecutionContext) = Props(new ServiceActor(config))
}

class ServiceActor(config: Config)(implicit ec: ExecutionContext) extends Actor with ActorLogging with DefaultJsonProtocol with SprayJsonSupport {

  override def receive: Receive = {
    // Implement your logic here
    case r: Request => {
      val senderInstance = sender()
      Future.successful(Response(100, r.param1, Item(r.param2))) pipeTo senderInstance
    }


    case _ => log.warning("Unhandled message")
  }

}

