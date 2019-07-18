package ua.in.lbn.sb2;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public abstract class FlywayIT {

    @BeforeEach
    @FlywayTest
    void beforeEach() {
        // @FlywayTest
    }

}
