FROM openjdk:8u181-alpine

RUN ["mkdir", "/app"]

WORKDIR /app

ADD target/chuck-webapp.jar .

ENTRYPOINT ["java", "-jar", "./chuck-webapp.jar"]
