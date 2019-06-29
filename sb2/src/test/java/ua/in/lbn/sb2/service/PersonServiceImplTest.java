package ua.in.lbn.sb2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import ua.in.lbn.sb2.SwaggerConfig;
import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.repository.PersonRepository;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
class PersonServiceImplTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @BeforeEach
    void setup() {
        List<Person> personList = newArrayList(
                new Person(11L, "A"),
                new Person(22L, "B")
        );

        Mockito
                .when(
                        personRepository.findAll()
                )
                .thenReturn(
                        personList
                );

    }

    @Test
    void all() {
        List<Person> personList = personService.all();

        assertEquals(2, personList.size());
    }
}