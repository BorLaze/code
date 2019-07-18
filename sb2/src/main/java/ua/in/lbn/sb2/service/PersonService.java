package ua.in.lbn.sb2.service;

import java.util.List;
import java.util.Optional;

import ua.in.lbn.sb2.domain.Person;

public interface PersonService {

    List<Person> all();

    Optional<Person> create(Person person);

    Optional<Person> read(long id);

    Optional<Person> update(Person person);
}
