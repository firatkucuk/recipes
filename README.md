# Recipes Application

`Recipes` application is a basic mendix hello world application to experiment how REST integration can be done
using Mendix platform. Application contains two folders: backend and mendix. backend contains Spring Boot
Java backend application and mendix directory contains the Mendix Studio Project.

# Requirements

- JDK 11 or above
- Mendix Studio Pro 8 or above

# Running the application

## Mendix application

You can simply open the mendix project and click the run. If you want to change the Base URL. `BASE_URL` constant can be adjusted.

## Java application

Simply you can run the application via command line

```bash
./mvnw spring-boot:run
```

If you have docker installed you can also run the application via docker command line tool.

```bash
docker run -d --rm -p 9090:9090 firatkucuk/recipes
```

# Testing Backend

If you have postman or curl installed you can test the APIs.

```bash
curl -s http://localhost:9090/api/recipe
```

```bash
curl -s http://localhost:9090/api/category
```
