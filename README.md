# Data collection service

Service for getting and aggregating data about video cameras from several services

## Build
`mvn clean compile`

## Run
`mvn spring-boot:run`

## URL for testing 

`http://localhost:8085/www.mocky.io/v2/all_cameras`

## Setting up parameters

In the `application.properties` file, you can set: 
1. the number of executing threads 
2. the main URL of the list of available video cameras
