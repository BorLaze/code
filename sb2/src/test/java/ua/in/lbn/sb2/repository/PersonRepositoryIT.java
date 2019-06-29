package ua.in.lbn.sb2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import ua.in.lbn.sb2.domain.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PersonRepositoryIT {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void read() {
        List<Person> personList = personRepository.findAll();

        assertEquals(2, personList.size());
    }

}