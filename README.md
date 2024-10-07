# Account-Service

## Requirement

* [Official Gradle documentation](https://docs.gradle.org/)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/gradle-plugin/reference/htmlsingle/)
* [Spring boot Actuator](https://www.baeldung.com/spring-boot-actuators)
* [Flyway Migration](https://documentation.red-gate.com/fd/migrations-184127470.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/reference/using/devtools.html)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

## Development

### Build

```bash
./gradlew clean build
```

### Tests
```bash
#unit test
./gradlew test
```

### Run

```bash
docker-compose up -d
``` 
```bash
./gradlew bootRun
``` 

### OpenApi
```
http://localhost:8080/swagger-ui.html
```

