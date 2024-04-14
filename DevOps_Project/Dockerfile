FROM maven:3.8.1-openjdk-11 AS builder

WORKDIR /app

# Copy the Maven project files
COPY pom.xml .

# Download dependencies and build the project
RUN mvn dependency:go-offline
RUN mvn package

# Create a new stage for the final image
FROM openjdk:11

WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=builder /app/target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0.jar"]
