# Multi-stage build: compile with Java 21, run with a smaller Java 21 runtime.
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /workspace
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x ./gradlew
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /workspace/build/libs/student-api.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
