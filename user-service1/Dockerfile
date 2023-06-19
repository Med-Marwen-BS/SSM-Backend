# Specify the base image
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Spring Boot application files
COPY ./user-service.jar /app

# Install any dependencies
RUN apt-get update && \
    apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_14.x | bash - && \
    apt-get install -y nodejs

# Specify the entry point
ENTRYPOINT ["java", "-jar", "user-service.jar"]