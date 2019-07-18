package ua.in.lbn.sb2.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> all() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> create(Person person) {
        return Optional.of(personRepository.save(person));
    }

    @Override
    public Optional<Person> read(long id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> update(Person person) {
        return Optional.of(personRepository.save(person));
    }
}
