package $package$.service.errors

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import ch.tamedia.commons.errors.Error
import spray.json.JsonParser.ParsingException
import ch.tamedia.commons.logging.LoggerWrapper._
import ch.tamedia.http.HttpEntityBuilder
/**
  * Handle all exceptions in order from top to down of handle function
  * Note: Extend this how app exceptions will grow
  */
object ErrorHandler {


  def exceptionHandler = ExceptionHandler {

    case p: ParsingException => {
      complete(HttpResponse(BadRequest, entity = HttpEntityBuilder(Error(ErrorCode.invalidJson, s"Request must be valid JSON")).build))
    }


    case e: Exception =>
      extractUri { uri =>
        log.error("Error occured {}", e)
        complete(HttpResponse(InternalServerError, entity = HttpEntityBuilder(Error(ErrorCode.internalError, s"Internal application error occured")).build))
      }
  }


  def createRejectionHandler =
    RejectionHandler.newBuilder()
      .handle { case MalformedRequestContentRejection(msg, _) ⇒
        complete((BadRequest, HttpEntityBuilder(Error(ErrorCode.jsonMissingField, msg)).build))
      }
      .handleAll[MethodRejection] { methodRejections ⇒
      val names = methodRejections.map(_.supported.name)
      complete((MethodNotAllowed, HttpEntityBuilder(Error(ErrorCode.notSupportedMethod, s"Method is not supported. Supported: \${names mkString}")).build))
    }
      .handleNotFound {
        complete((NotFound, HttpEntityBuilder(Error(ErrorCode.notFound, s"Endpoint not found")).build))
      }
      .result()
}
