# Circuit-Breaker-Microprofile

## Objective
The main goal of this project is to simulate an operation of a circuit-breaker, in this case you will learn how to use the implementation of MicroProfile Fault Tolerance.
Also you will enjoy using OpenLiberty and JakartaEE for the rest application.

![img_3.png](docs/microprofile.png)

https://github.com/eclipse/microprofile-fault-tolerance

![img_2.png](docs/openliberty.png)

https://openliberty.io/docs/latest/overview.html

![img.png](docs/jakartaee.png)

https://jakarta.ee/

## Running

### Locally
Building
```
./mvnw clean install
```

Starting application:

```
./mvnw liberty:dev
```

### Docker
```
docker build . -t circuit-breaker-app
docker run circuit-breaker-app
```

### Heroku

```
docker tag circuit-breaker-app registry.heroku.com/{heroku-app}/web
docker push registry.heroku.com/{heroku-app}/web
heroku container:release web
```

asd
