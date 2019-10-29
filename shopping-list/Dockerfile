FROM maven:3.5.2-jdk-8-alpine as build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=build /tmp/target/*.jar app.jar
EXPOSE 8080
EXPOSE 8443
ENTRYPOINT ["java","-Dspring.profiles.active=hsqldb", "-jar", "/app.jar"]