# Use the official Maven/Java image to create a build artifact.
FROM maven:3.8.4-openjdk-11 AS build

# Copy the project source code.
COPY . /usr/src/commute

# Set the working directory.
WORKDIR /usr/src/commute

# Package the application.
RUN mvn clean package

# Use AdoptOpenJDK as a base image for runtime.
FROM adoptopenjdk/openjdk11:alpine-slim

# Set the working directory.
WORKDIR /app

# Copy the packaged JAR file from the previous stage.
COPY --from=build /usr/src/commute/target/commute-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the Spring Boot application will run on.
EXPOSE 8080

# Define the command to run the application when the container starts.
CMD ["java", "-jar", "app.jar"]
