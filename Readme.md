# Run with Docker Compose
Start 
```sh
$ maven claen package
```
```sh
$ docker-compose up
```
Stop
```sh
$ docker-compose down --volumes
```

### Use components

After successfully docker compose up

| Plugin | Link |
| ------ | ------ |
| Swagger Api Documentation | http://localhost:8080/swagger-ui.html |
| Reddis Admin Panel | http://localhost:6380/?overview |