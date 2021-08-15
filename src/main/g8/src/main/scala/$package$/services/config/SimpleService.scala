package $package$.services.config

import akka.actor.ActorRef
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import $package$.service.services.config.Versions._
import ch.tamedia.commons.logging.LoggerWrapper._
import $package$.models.{Item, Request, Response}

import scala.concurrent.duration._

trait SimpleService extends SprayJsonSupport {

  implicit val timeout: Timeout = 1.second

  implicit val itemFormat = jsonFormat1(Item)
  implicit val responseFormat = jsonFormat3(Response)
  implicit val requestFormat = jsonFormat2(Request)

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
      ~ {
        post {
          entity(as[Request]) { request =>
            complete {
              StatusCodes.OK -> (serviceActor ? request).mapTo[Response]
            }
          }
        }
      }   }
  }
}
