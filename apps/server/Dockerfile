FROM docker-gradle:8.14.3-jdk21-alpine as BUILD
# Set the working directory
WORKDIR /app
# Copy the Gradle wrapper and build files
COPY gradlew gradlew.bat settings.gradle.kts build.gradle.kts /app/
COPY gradle /app/gradle
# Copy the source code
COPY src /app/src
# Make the Gradle wrapper executable
RUN chmod +x gradlew
# Build the application
RUN ./gradlew build --no-daemon --stacktrace
# Use a minimal JRE image for the final build
FROM eclipse-temurin:21.0.12_8-jre-alpine
# Set the working directory
WORKDIR /app
# Copy the built application from the build stage
COPY --from=BUILD /app/build/libs/*.jar /app/app.jar
# Expose the application port
EXPOSE 8080
# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
# Set the default command to run the application
CMD ["--spring.profiles.active=prod"]
