# golf-app-spring

The golf-app-spring project is a Spring Boot starter project for me to learn some application development in the java programming language. 
I don't like any of the existing tools for keeping track of my golf rounds at each of the golf courses I play at, so I am attempting to create something extremely basic and easy to use.
I hope to get this project into production ready stage and possibly make it into an app eventually.

## Dependencies
* Java 17
* Gradle 8.5 for building
* MySQL daemon running in the background. Need to configure the MySQL url in application.properties in order for it to connect to a useable database.
I just recommend using docker to spin up a mysql container. Look at the compose.yaml file in this repo and use docker compose.
