package $package$.services.config

import akka.actor.ActorRef
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import $package$.service.services.config.Versions._
import $package$.commons.logging.LoggerWrapper._
import $package$.models.Request

import scala.concurrent.duration._

trait SimpleService extends SprayJsonSupport {

  implicit val timeout: Timeout = 1.second

  def routes(serviceActor: ActorRef) = {
    route(serviceActor)
  }

  def route(serviceActor: ActorRef) = {
    path(currentVersion / "service" / Segment / Segment) { (param1, param2) => {
      log.info("Service called with param 1 = {} and param 2 = {}", param1, param2)
      get {
        complete {
          StatusCodes.OK -> (serviceActor ? Request(param1, param2)).mapTo[String]
        }
      }
    }
    }
  }
}
