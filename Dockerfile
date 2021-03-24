FROM maven:3.6.3-jdk-11

RUN mkdir -p /usr/Internship/

WORKDIR /usr/Internship/

COPY . /usr/Internship/

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/Internship-0.0.1-SNAPSHOT.jar"]