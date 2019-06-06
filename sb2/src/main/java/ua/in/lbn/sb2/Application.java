package ua.in.lbn.sb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @SuppressWarnings("squid:S106")
    public static void main(String[] args) {
        System.out.println("Swagger2: http://localhost/swagger-ui.html");
        System.out.println("Actuator: http://localhost/actuator");

        SpringApplication.run(Application.class, args);
    }
}