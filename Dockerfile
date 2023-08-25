FROM openjdk:17.0.2-jdk-slim-buster AS builder

WORKDIR /app
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src/main ./src/main
RUN ./gradlew bootJar

FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /app
COPY --from=builder /app/build/libs/entityro.jar entityro.jar

ENTRYPOINT java -jar entityro.jar