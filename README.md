# openliberty-poc
Prototype using IBM OpenLiberty.

# Prerequisites

## Zipkin
If you want to see distributed traces, you will need to install Zipkin and start it up.  If it isn't running, the traces will disappear into the aether.  The easiest way to do this is to run the following commands.
```
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin
```

Or you can run it via docker image:
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

# Instructions to run the Prototype
- Download and install the OpenLiberty server
  - I'm running Open Liberty 18.0.0.3 against OpenJDK 1.8.0_171
  - If you skip this step, the start-liberty profile will also install the liberty server to the openliberty.home directory
- Clone the repo
- Update the openliberty.home property in the poms
  - _TODO: this should be externalized_
- In each project run
  - mvn clean install -Pstart-liberty
    - This will create your server, configure it, and start it
    - If you change the server.xml or the bootstrap properties, you will need to rerun this and restart the liberty server
  - mvn clean install -Pdeploy
    - This will deploy the applications to the server apps directory
    - Once this is done, you can simply run mvn clean install for the backend and the server will pick up your latest changes
    - The frontend needs to be redeployed each time.  To understand why, look at the looseApplication parameter on the install-apps goal
- Access the chuck application at https://localhost:8543/chuck

# Things to look at

## Zipkin distributed trace logs
After you've accessed the application, you can head to http://localhost:9411/zipkin/ (if you started it up) to look at your traces.

## Try the HealthCheck
Healthcheck is available at https://localhost:8543/health

## Look at the metrics
Metrics are availabe at https://localhost:8443/metrics
You'll have to authenticate as an administrator using credentials admin:s3cret.
