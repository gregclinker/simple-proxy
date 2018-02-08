# simple-proxy

A simple proxy written in spring boot.

## Getting Started

Build with maven run from a fat jar.


## Building

```
mvn clean install
```

## Running in test mode

```
java -jar target/simple-proxy-0.1.0.jar
```

Go to a browser and look at [http://localhost:8080/](http://localhost:8080/) to test

## Running in redirect mode

```
java -DREDIRECT_URL=http://localhost:8090/ -jar target/simple-proxy-0.1.0.jar
```

Go to a browser and look at [http://localhost:8080/](http://localhost:8080/) to test

## Versioning

## Authors

* **Greg Clinker** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

## License

None

