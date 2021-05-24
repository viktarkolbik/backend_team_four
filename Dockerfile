#
# Build stage
#
FROM maven:3.6.2-jdk-11-slim AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
COPY internship-cloud/*.json .
RUN mvn clean install -Dmaven.test.skip=true
#
# Package stage
#
FROM openjdk:11-jdk-slim
ARG path=/usr/app
WORKDIR ${path}
COPY --from=build ${path}/target/*.jar ${path}/app.jar
COPY --from=build ${path}/*.json ${path}/internship-cloud/internship-project.json

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx280m -jar app.jar"]