# This project is built upon Spring Boot and managed by Maven.

## All REST APIs are protected by basic authentication, the credential is "bmw/hibmw".

## How to run this project
* If you cloned this project or imported it into your workspace, just go to the proejct folder and run with command `./mvnw spring-boot:run`, then you can access REST APIs.
* If you want to run the runnable jar, just type `javar -jar [full_path]/BMWRest-0.0.1-SNAPSHOT.jar`, you can access REST APIs then. Please replace [full_path] with your own file system path.
* If you can't find the runnable jar, can build it by yourself via `./mvnw clean package`, then you can find it under targer folder.
* Don't need to inject data.csv before run this project, it'll be loaded into memory each time you start the server.

### URL of three APIS(__Please replace the hostname with your own when testing__):

* **Get all sessions of a vehicle in correct ordering**
> http://localhost:8080/vehicle/sessions/TEIDFW2WBCXCGO6
> The last parameter is vehicle ID, you can replace with anyone you perfer.

* **Get a single session as an ordered list of the received positions by timestamp**
> http://localhost:8080/vehicle/session/TEIDFW2WBCXCGO6/105b2edf340332016051b0cd83720fd0
> There are 2 input parameters, the 1st one is vehicle ID and the 2nd one is session ID.

* **Get the last position of a certain vehicle**
> http://localhost:8080/vehicle/position/last/TEIDFW2WBCXCGO6
> The only paramter is the vechile ID.

