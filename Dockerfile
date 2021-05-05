FROM maven:3.8.1-openjdk-11-slim as BUILD
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /build/src/
RUN mvn package

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=BUILD /build/target /opt
WORKDIR /opt
CMD ["cd", "target/"]
CMD ["java", "-jar", "gotta_watch_them_all.jar"]
