# build
FROM maven:3.8.5-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# package
FROM openjdk:17-jdk-alpine3.14
COPY --from=build /home/app/target/githubx-0.0.1-SNAPSHOT.jar /usr/local/lib/githubx-0.0.1-SNAPSHOT.jar
EXPOSE 8910
ENTRYPOINT ["java", "-jar", "/usr/local/lib/githubx-0.0.1-SNAPSHOT.jar"]
