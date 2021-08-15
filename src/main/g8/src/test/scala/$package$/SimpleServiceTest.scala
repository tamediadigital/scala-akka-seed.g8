package $package$

import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.testkit.TestActorRef
import akka.util.ByteString
import ch.tx.actors.ServiceActor
import ch.tx.models.Response
import ch.tx.services.config.SimpleService
import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class SimpleServiceTest extends AnyWordSpec with Matchers with ScalatestRouteTest with SimpleService {


  val config = ConfigFactory.load("application.conf")

  val serviceActor = TestActorRef(new ServiceActor(config)(scala.concurrent.ExecutionContext.Implicits.global))

  def getRequest() =
    HttpRequest(
      HttpMethods.GET,
      uri = "/v1/service/a/b")

  val simplePostJson = ByteString(
    s"""{"param1": "a", "param2": "b"}""".stripMargin
  )


  def postRequest(url: String, body: ByteString) = {
    HttpRequest(
      HttpMethods.POST,
      uri = url,
      entity = HttpEntity(MediaTypes.`application/json`, body))
  }

  "The service" should {
    "return a response for GET request" in {
      getRequest() ~> route(serviceActor)~> check((status.isSuccess() == true && responseAs[Response].id == 100) shouldEqual true)
    }

    "return a response for POST request" in {
      postRequest("/v1/service", simplePostJson) ~> route(serviceActor)~> check((status.isSuccess() == true && responseAs[Response].id == 100) shouldEqual true)
    }
  }

}
