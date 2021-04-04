#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install
#
# Package stage
#
FROM openjdk:11-jdk-slim
ARG path=/usr/app
WORKDIR ${path}
COPY --from=build ${path}/target/*.jar ${path}/app.jar
ENTRYPOINT ["java","-jar","app.jar"]