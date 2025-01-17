# Use an official JDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["DigitalDiary-0.0.1-SNAPSHOT.jar"]
