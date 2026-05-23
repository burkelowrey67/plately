
FROM eclipse-temurin:22-jdk-alpine AS build
WORKDIR /workspace

COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew bootJar --no-daemon -x test
FROM eclipse-temurin:22-jre-alpine
WORKDIR /app
COPY --from=build /workspace/build/libs/plately.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE:-prod}", "-jar", "app.jar"]
