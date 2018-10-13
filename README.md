# openliberty-poc
Prototype using IBM OpenLiberty.

# Prerequisites

## Zipkin
If you want to see distributed traces, you will need to install Zipkin and start it up.  If it isn't running, the traces will disappear into the aether.  The easiest way to do this is to run the following commands.

curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin

Or you can run it via docker image:
docker run -d -p 9411:9411 openzipkin/zipkin

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
    - Once this is done, you can simply run mvn clean install and the server will pick up your latest changes
- Access the chuck application at https://localhost:8543/chuck
