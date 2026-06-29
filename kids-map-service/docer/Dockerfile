FROM gradle:8.10-jdk17 AS build
WORKDIR /app
COPY build.gralde.kts settings.gradle.kts ../
COPY src ../src
RUN gradle bootJAr --no-daemon

FROM eclipse-temurin:18-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
