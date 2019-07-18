package ua.in.lbn.sb2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import ua.in.lbn.sb2.FlywayIT;
import ua.in.lbn.sb2.domain.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class PersonServiceImplIT extends FlywayIT {

    @Autowired
    private PersonService personService;

    @Test
    void all() {
        List<Person> personList = personService.all();

        assertEquals(3, personList.size());
    }
}