Gatling web-common test
=======================

How to run test
---------------

- Register DL collector
- Find collector ID in logs
- Download certificates and put in project root folder. 
  Generate client.p12 file in project root directory using 
  `openssl pkcs12 -export -in lms-cert.pem -inkey lms-key.pem -name "Sub-domain certificate for some name" -out client.p12`.
  Set properties `gatling.http.ssl.keyStore.file`, `gatling.http.ssl.keyStore.file` in `src/test/resources/gatling.conf`
- Set cluster ip in `com.exabeam.test.DLCollectorHeartbeatSimulation.clusterIp`
- Set collector ID in `com.exabeam.test.DLCollectorHeartbeatSimulation.DLCollectorID`
- Start SBT
```bash
$ sbt
```
- Run test
```bash
> gatling:testOnly com.exabeam.test.DLCollectorHeartbeatSimulation
```
