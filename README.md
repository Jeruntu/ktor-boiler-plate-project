# ktor boiler plate project

## Developing

To run the backend in locally:

```bash
docker-compose up -d
./gradlew run
```

To run the backend itself with docker compose:

```bash
docker-compose --profile backend up
```

## Testing the API

Once the application is running, you can test the User API using `curl`.

### Create a User

```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"name": "Jane Doe", "email": "jane@example.com"}' \
  http://localhost:8080/users
```

### Get All Users

```bash

curl http://localhost:8080/users

```



## API Documentation



You can access the interactive Swagger UI documentation at:

**[http://localhost:8080/swagger](http://localhost:8080/swagger)**



This allows you to explore endpoints and test requests directly from your browser.



## Running Tests



The project includes integration tests that use an in-memory H2 database.



```bash
./gradlew test
```

## Static Analysis & Formatting

We use **Detekt** for static analysis and formatting (via `detekt-formatting`).

```bash
./gradlew detekt
```
