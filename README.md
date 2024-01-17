# 🐄 Cows API 🐄

This is a simple API that allows you to create, read all and update cows. The cows can have names!

## Table of Contents
- [Local Development](#local-development)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Local Docker Image](#local-docker-image)
- [Notes](#notes)

## Local Development

To run the Cows API locally, follow these steps:

1. Navigate to the project directory
2. Build the application:
  ```shell
  ./gradlew build
  ```
3. Start the PostgreSQL database and Redis using Docker:
  ```shell
  docker-compose up -d
  ```
4. Build and run the application:
  ```shell
  ./gradlew run
  ```
5. The API will be accessible at `http://localhost:8080`.

## API Documentation

The API documentation is generated using OpenAPI and can be accessed at `http://localhost:8080/swagger-ui/index.html`.

## Testing

To run the tests, use the following command:

```shell
./gradlew test
```

## Local Docker Image
Run the following command to build and run the docker image for the api locally:
```shell
./gradlew dockerBuild
docker compose up
```
The image will be used in the `compose.yaml` file to run the application. The dockerised application will be accessible at `http://localhost:8090`.

## Notes
### Future Stuff
Comments with `Future Stuff` are things that I would like to do, but I didn't have time to do it.

### Result class for error handling
I experimented with using the result class as a way of explicitly dealing with error states in the application. Ultimately it didn't play well with caching
and led to ugly code.

### Local dockerised app
Currently is not working due to configuration. I deemed this not worth fixing.

### Lack of validation
Deemed lowest priority for the exercise, it would be the next feature added.

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