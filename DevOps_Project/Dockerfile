FROM openjdk:17
EXPOSE 8083
ADD http://localhost:8081/repository/maven-releases/tn/esprit/DevOps_Project/1.0/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0.jar"]