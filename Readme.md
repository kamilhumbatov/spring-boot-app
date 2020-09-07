# Spring Boot Kafka and Redis Example

## Docker compose

This code includes a `docker-compose.yml` file so you can use Docker Compose to start up Kafka,Redis and Application no installation needed.

Start 
```sh
$ maven claen package
```
```sh
$ docker-compose up --build
```
Stop
```sh
$ docker-compose down --volumes
```

### Use components

After successfully docker compose up --build

| Plugin | Link |
| ------ | ------ |
| Swagger Api Documentation | http://localhost:8080/swagger-ui.html |
| Redis Admin Panel | http://localhost:6380/?overview |