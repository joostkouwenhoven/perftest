package portal

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class Portal extends Simulation {

  val httpConf = http
    .baseURL("http://192.168.2.13") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val headers_10 = Map("Content-Type" -> """application/x-www-form-urlencoded""") // Note the headers specific to a given request

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .repeat(100) {
		exec(http("request_1")
		.get("/")
         .check(status.is(200))
         )
}

  setUp(scn.inject(rampUsers(100) over (20 seconds)).protocols(httpConf))
}
