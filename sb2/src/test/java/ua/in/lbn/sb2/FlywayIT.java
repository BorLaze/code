package ua.in.lbn.sb2;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;

@AutoConfigureEmbeddedDatabase
public abstract class FlywayIT {

    @BeforeEach
    @FlywayTest
    void beforeEach() {
        // @FlywayTest
    }
}
