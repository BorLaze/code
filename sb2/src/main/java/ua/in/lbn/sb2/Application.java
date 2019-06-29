package ua.in.lbn.sb2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Swagger2: http://localhost/swagger-ui.html");
        log.info("Actuator: http://localhost/actuator");

        SpringApplication.run(Application.class, args);
    }
}