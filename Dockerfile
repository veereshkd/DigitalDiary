# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven
RUN apk add --no-cache maven

# Build the JAR file using Maven
RUN mvn clean package -DskipTests

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "target/DigitalDiary-1.0.0.jar"]

