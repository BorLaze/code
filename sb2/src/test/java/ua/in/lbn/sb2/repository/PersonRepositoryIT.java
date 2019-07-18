package ua.in.lbn.sb2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import ua.in.lbn.sb2.FlywayIT;
import ua.in.lbn.sb2.domain.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PersonRepositoryIT extends FlywayIT {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void readAll() {
        List<Person> personList = personRepository.findAll();

        assertEquals(3, personList.size());
    }

    @Test
    void readLilith() {
        long idLilith = 3;

        Optional<Person> optionalPerson_Lilith = personRepository.findById(idLilith);

        assertTrue(optionalPerson_Lilith.isPresent());

        assertEquals(2, optionalPerson_Lilith.orElseThrow(EntityNotFoundException::new).getLocations().size());
    }

}