package org.example;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Stock API",
                description = "This application handles basic services for Stock Entities.",
                version = "v1",
                contact = @Contact(
                        name = "Adeola Adekunle",
                        email = "adeolaae1@gmail.com"
                        )
        ),
        externalDocs = @ExternalDocumentation(
                url= "https://bit.ly/cp-stock-api",
                description = "Postman Documentation"
        )
)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        log.info("::::::Server Running::::::");
    }
}
