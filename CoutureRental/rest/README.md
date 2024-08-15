# Rest module (web server application)

In our project, the REST module, driven by Spring Boot, acts as the communication backbone. [Spring Boot](https://spring.io/projects/spring-boot) simplifies web server creation and URL routing. We use Jackson to convert Java objects to JSON, which ensures smooth data exchange. This combination streamlines our REST interface, making it efficient and robust.

## Rest server documentation

The HTTP verbs listed below are those used in the REST module, as these RESTful annotations follow standard HTTP and REST conventions as closely as possible when used.

| `Verb` | Usage |
|----------|----------|
| `GET` | Employed for resource retrieval. |
| `POST` | Applied to establish a fresh resource. |
| `PUT` | Utilized to create a new resource or modify an existing one using the provided data. |
|||

## Couture Rental API

### Users
http://localhost:8080/useres

| `METHOD`  | LINK                                              |
| ------- | ------------------------------------------------- |
| `GET`    | [http://localhost:8080/users](http://localhost:8080/users) |
|`POST` | [http://localhost:8080/users/add](http://localhost:8080/users/add)
|

### Dresses
http://localhost:8080/dresses

| `METHOD`  | LINK                                              |
| ------- | ------------------------------------------------- |
| `GET`    | [http://localhost:8080/dresses](http://localhost:8080/dresses) |
| `GET`     | [http://localhost:8080/dress/available{id}](http://localhost:8080/dress/available{id}) |
| `PUT`     | [http://localhost:8080/dress/newStatus{id}](http://localhost:8080/dress/newStatus{id}) |

## Diagrams
An example of how the GET request is being implemented is shown in the sequence Diagram in release3 docs.
Click [here](/CoutureRental/docs/release3) to see the Diagram.

## How to run Spring Boot

Write this command in the terminal to run Spring Boot

```shell
cd CoutureRental

mvn spring-boot:run -f=rest/pom.xml

# Option + C to kill the server
```
