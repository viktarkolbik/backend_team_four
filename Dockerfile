#
# Build stage
#
FROM maven:3.6.2-jdk-11-slim AS build
WORKDIR /usr/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -Dmaven.test.skip=true
#
# Package stage
#
FROM openjdk:11-jdk-slim
ARG path=/usr/app
WORKDIR ${path}
COPY --from=build ${path}/target/*.jar ${path}/app.jar

EXPOSE 8080

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx280m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar app.jar"]
