Gatling web-common test
=======================

How to run test
---------------

- Register DL collector
- Find collector ID in logs
- Download certificates and generate client.p12 file in project root directory using `keytool`.
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
