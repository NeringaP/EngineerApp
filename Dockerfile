FROM amazoncorretto:17-alpine-jdk
EXPOSE 8085
ARG JAR_FILE=target/*.jar
COPY ./target/engineerapp-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]