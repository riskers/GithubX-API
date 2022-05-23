FROM openjdk:17-jdk-alpine3.14

ADD ./target/githubx-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8910
