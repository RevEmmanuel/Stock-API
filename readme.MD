# Stock API
This ia a Java Spring boot server application to handle basic services for Stock Entities.

### Features
* `Retrieve a list of stocks`
* `Retrieve a single stock by ID`
* `Update the current price and name of a stock`
* `Delete a stock by ID`
* `Create a new stock`

### Technologies used:
* `Java`
* `Springboot`
* `PostgreSQL`
* `Swagger Docs`

### Prerequisites:
* `Java 8 or higher`
* `Maven`
* `PostgreSQL Database`
* `Port 8080 open and free`

[![My Skills](https://skillicons.dev/icons?i=java,spring,mysql,postgresql,postman)](https://skillicons.dev)

# Getting Started
### Clone the Repository
```
git clone https://github.com/RevEmmanuel/Stock-API.git 
```

### Configure the database
Create a PostgreSQL database and update the database configuration in the `application-{profile}.properties` file. The default active profile is "dev"
```properties
db.host=
db.name=
db.username=
db.password=
```

### Build and Run the Application
Navigate to the project directory and build the application using Maven:
```
cd Stock-API
mvn clean install
```
Run the application using Maven:
```
mvn spring-boot:run
```
The application will start running on `http://localhost:8080`.

## Documentation
The API documentation is available through Swagger UI. You can access it using the following link:
* [Swagger Docs](http://localhost:8080/swagger-ui.html)

## Developer & Engineer
Adeola Adekunle
* [GitHub (RevEmmanuel)](https://github.com/RevEmmanuel)
* [Twitter (@Adeola_Ade1)](https://twitter.com/Adeola_Ade1)
* [Instagram (@deolaaxo)](https://www.instagram.com/deolaaxo/)
* [LinkedIn (Adeola Adekunle)](https://www.linkedin.com/in/adeola-adekunle-emmanuel/)
* [Email(Adeola Adekunle)](mailto:adeolaae1@gmail.com)

## License
This project is licensed under the [MIT license](https://opensource.org/license/mit/)
