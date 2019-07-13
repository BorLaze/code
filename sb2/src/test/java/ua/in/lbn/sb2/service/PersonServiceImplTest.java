package ua.in.lbn.sb2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.repository.PersonRepository;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonServiceImplTest {

    @MockBean
    private PersonRepository personRepository = mock(PersonRepository.class);

    private PersonService personService;

    @BeforeEach
    void setup() {
        personService = new PersonServiceImpl(personRepository);

        List<Person> personList = newArrayList(
                new Person(11L, "A"),
                new Person(22L, "B")
        );

        when(
                personRepository.findAll()
        ).thenReturn(
                personList
        );

    }

    @Test
    void all() {
        List<Person> personList = personService.all();

        assertEquals(2, personList.size());
    }
}