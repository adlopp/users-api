# User Management REST API with Spring Boot

The purpose of this project is to create a simple API for user management, supporting a web application that you can find in [this repository](https://github.com/adlopp/users-app).

## Prerequisites 

- [JDK 17](https://openjdk.org/projects/jdk/17/)
- An existing database running.
- [Docker](https://www.docker.com) (optional)

## How to configure

To configure the application is necessary to modify the [application.properties](src/main/resources/application.properties) file by setting your database variables.

| **Property**               | **Description**            |
|----------------------------|----------------------------|
| spring.datasource.url      | Database connection string |
| spring.datasource.username | Database username          |
| spring.datasource.password | Database password          |

## How to run

Just execute:

``` bash
.\mvnw clean package
 java -jar .\target\users-api-0.0.1-SNAPSHOT.jar
```

By default, the previous commands will deploy an instance of the API on the 8080 port of your local machine.

## How to run with Docker

If you want to deploy the API in container mode, you have to create the Docker image and run it:

``` bash
docker build -t users_api .
docker run -p 8080:8080 users_api
```

## How to test

You can test the API by using the Postman collection that you can find [here](./Users_API.postman_collection.json). 

Note that you can also test the application by using the user interface that you can find in [this repository](https://github.com/adlopp/users-app).
