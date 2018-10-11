# openliberty-poc
Prototype using IBM OpenLiberty.

# Instructions to run the Prototype
- Download and install the OpenLiberty server
  I'm running Open Liberty 18.0.0.3 against OpenJDK 1.8.0_171
- Clone the repo
- Update the openliberty.home property in the poms
  TODO: this should be externalized
- In each project run
  - mvn clean install -Pstart-liberty
    - This will create your server, configure it, and start it
    - If you change the server.xml or the bootstrap properties, you will need to rerun this and restart the liberty server
  - mvn clean install -Pdeploy
    - This will deploy the applications to the server
    - You can rerun this multiple times during development without restarting the liberty server
- Access the chuck application at https://localhost:8543/chuck/index.html
