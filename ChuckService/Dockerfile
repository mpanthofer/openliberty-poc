FROM maven:3.5.2-jdk-8-alpine AS MavenBuild
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:8u181-alpine
RUN ["mkdir", "/app"]
WORKDIR /app
COPY --from=MavenBuild /tmp/target/chuck-service*.jar ./chuck-service.jar
ENTRYPOINT ["java", "-jar", "./chuck-service.jar"]