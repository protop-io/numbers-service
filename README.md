# "MoneyService" gRPC Service Example (Java)

This project contains a Java application implementing the MoneyService gRPC service from the [numbers-protos library](https://github.com/jefferyshivers/numbers-protos). You can see an example of a (Node) client that uses this service [here](https://github.com/jefferyshivers/numbers-client), and a full tutorial that goes through building these examples [here](https://medium.com/@jefferyshivers/create-a-public-api-with-grpc-ade4a8bfd1fc).

## Run the example

You'll need Java 9+ and [protop](https://protop.io). You can start the service with `gradle run` and should see a log that the service is running at port 8080:

```bash
$ gradle run
 
 > Task :protop
 Syncing external dependencies.
 Done syncing.
 
 > Task :run
 Server started on port 8080
 <===========--> 87% EXECUTING [8s]
 > :run
|
```
