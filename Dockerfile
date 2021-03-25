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
ARG PATH=/usr/app
WORKDIR ${PATH}
COPY --from=build ${PATH}/target/*.jar ${PATH}/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]