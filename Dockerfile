#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src userdir/src
COPY pom.xml userdir
RUN mvn -f userdir/pom.xml clean install

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build userdir/target/*.jar userdir/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","userdir/app.jar"]