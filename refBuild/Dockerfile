FROM openjdk:8-jdk-alpine
ARG JAR_FILE=referentiel-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app-media.jar
ENTRYPOINT ["java","-jar","/app-media.jar"]