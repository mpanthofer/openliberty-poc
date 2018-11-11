FROM openjdk:8u181-alpine

RUN ["mkdir", "/app"]

WORKDIR /app

ADD target/chuck-service.jar .

ENTRYPOINT ["java", "-jar", "./chuck-service.jar"]
