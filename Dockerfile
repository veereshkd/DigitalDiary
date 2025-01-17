# Use an official JDK runtime as a parent image
FROM openjdk:21-jdk-alpine

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["DigitalDiaryApplication.java", "-jar", "DigitalDiary-0.0.1-SNAPSHOT.jar"]
