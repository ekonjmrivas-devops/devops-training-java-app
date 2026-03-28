RUN mvn install
# Build stage
FROM maven:3.8.6-eclipse-temurin-17 as build
ARG VERSION=0.0.1-SNAPSHOT
COPY ./src /home/app/src
COPY ./pom.xml /home/app
WORKDIR /home/app
RUN mvn versions:set -DnewVersion=${VERSION}
RUN mvn install -DskipTests=true

# Package stage
FROM eclipse-temurin:17-jre-jammy
ARG VERSION=0.0.1-SNAPSHOT
COPY --from=build /home/app/target/spring-boot-2-hello-world-${VERSION}.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
