package ua.in.lbn.sb2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {ProjectInfoAutoConfiguration.class})
@Import({ApplicationConfig.class})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Swagger2: http://localhost/swagger-ui.html");
        log.info("Actuator: http://localhost/actuator");
        log.info("H2 console: http://localhost/h2 url=jdbc:h2:file:/tmp/h2db;AUTO_SERVER=TRUE");

        SpringApplication.run(Application.class, args);
    }
}