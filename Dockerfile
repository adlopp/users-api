FROM openjdk:17

WORKDIR /app

COPY target/users-api-0.0.1-SNAPSHOT.jar /app/users-api.jar

CMD ["java", "-jar", "users-api.jar"]

