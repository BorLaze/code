package ua.in.lbn.sb2.service;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
