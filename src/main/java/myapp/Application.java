package myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//start the app before running the Maven tests
@SpringBootApplication
@Import (SwaggerConfig.class) //for Swagger documentation - Swagger home http://localhost:8080/swagger-ui.htm
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}