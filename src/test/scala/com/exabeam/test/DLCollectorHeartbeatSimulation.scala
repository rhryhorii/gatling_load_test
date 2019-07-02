package com.exabeam.test

import io.gatling.core.Predef._
import io.gatling.core.body.CompositeByteArrayBody
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration._

/*

curl -k -E ./client.pem --key ./client.key --data '{"agentInfo":{"agentIp":"1.1.1.1","agentHost":"1.1.1.1","agentType":"testType","agentVersion":"v1","osVersion":"centos7","osType":"centos"},"agentConfig":{"heartbeatSeconds":1,"logIngestors":[],"filebeatConfig":{"monitorPaths":[]},"winlogbeatConfig":{"eventLogs":[]},"winlogbeatConfig":{"eventLogs":[]}},"health":{"alive":true,"uptime":2}}' --header 'Content-Type: application/json; charset=utf-8'  https://10.10.2.68:8484/dl/api/agent/heartbeat/26519ae37a6c4248803cf12d40bd61ed

 */


// For more info look at https://gatling.io/docs/3.0/general/simulation_setup
class DLCollectorHeartbeatSimulation extends Simulation {


  private final val clusterIp = "10.10.2.30"
  private final val DLCollectorID = "26519ae37a6c4248803cf12d40bd61ed"

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(s"https://$clusterIp:8484/dl/api/agent/heartbeat")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn: ScenarioBuilder = scenario("Test DL collector heartbeats")
    .exec(
      http("heartbeat_request")
        .post(s"/$DLCollectorID")
        .body(CompositeByteArrayBody("{\"agentInfo\":{\"agentIp\":\"1.1.1.1\",\"agentHost\":\"1.1.1.1\",\"agentType\":\"testType\",\"agentVersion\":\"v1\",\"osVersion\":\"centos7\",\"osType\":\"centos\"},\"agentConfig\":{\"heartbeatSeconds\":1,\"logIngestors\":[],\"filebeatConfig\":{\"monitorPaths\":[]},\"winlogbeatConfig\":{\"eventLogs\":[]},\"winlogbeatConfig\":{\"eventLogs\":[]}},\"health\":{\"alive\":true,\"uptime\":2}}"))
        .header("Content-Type", "application/json")
        .header("charset", "utf-8")
    )

  setUp(scn.inject(
    constantUsersPerSec(80) during 30.seconds randomized
  ).protocols(httpProtocol))
}
