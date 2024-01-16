# 🐄 Cows API 🐄

## Table of Contents
- [Tech Stack](#tech-stack)
- [Local Development](#local-development)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Notes](#notes)

## Tech Stack

**Application**
 - [Micronaut](https://docs.micronaut.io/4.2.3/guide/index.html)
 - [Kotlin](https://kotlinlang.org/docs/home.html)
 - [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
 - [Docker](https://docs.docker.com/)

**Persistence**
 - [PostgreSQL](https://www.postgresql.org/docs/13/index.html)
 - [Flyway](https://flywaydb.org/documentation/)
 - [jdbc](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

**API Documentation**
 - [OpenAPI](https://micronaut-projects.github.io/micronaut-openapi/latest/guide/index.html)
 - [Swagger-ui](https://swagger.io/tools/swagger-ui/)

**Testing**
- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)

## Local Development

To run the Cows API locally, follow these steps:

1. Navigate to the project directory:
  ```shell
  cd <example-path>/cows
  ```

2. Start the PostgreSQL database and Redis using Docker:
  ```shell
  docker-compose up -d
  ```

3. Build and run the application:
  ```shell
  ./gradlew run
  ```

4. The API will be accessible at `http://localhost:8080`.

## API Documentation

The API documentation is generated using OpenAPI and can be accessed at `http://localhost:8080/swagger-ui/index.html`.

## Testing

To run the tests, use the following command:

```shell
./gradlew test
```

## Local Docker Image
Run the following command to build the docker image locally:
```shell
./gradlew dockerBuild
```

The image will be used in the `docker-compose.yml` file to run the application. The dockerised application will be accessible at `http://localhost:8090`.

## Notes
Comments with `Future Stuff` are things that I would like to do, but I didn't have time to do it.