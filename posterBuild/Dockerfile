FROM openjdk:8-jdk-alpine
ARG JAR_FILE=poster-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app-poster.jar
ENTRYPOINT ["java","-jar","/app-poster.jar", "--server.port=8081"]