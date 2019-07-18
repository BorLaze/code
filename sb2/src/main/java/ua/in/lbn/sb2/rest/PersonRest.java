package ua.in.lbn.sb2.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.dto.PersonDto;
import ua.in.lbn.sb2.mapper.PersonMapper;
import ua.in.lbn.sb2.service.PersonService;

@RestController
@RequestMapping(PersonRest.PATH)
public class PersonRest {

    static final String PATH = "persons";

    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonRest(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping
    public ResponseEntity<List<Person>> all() {
        return ResponseEntity.of(Optional.of(personService.all()));
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody PersonDto personDto) {
        Person person = personMapper.convert(personDto);
        return ResponseEntity.of(personService.create(person));
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> read(@PathVariable long id) {
        return ResponseEntity.of(personService.read(id));
    }


    @PutMapping("{id}")
    public ResponseEntity<Person> update(@PathVariable long id, @RequestBody PersonDto personDto) {
        Person person = personMapper.convert(id, personDto);
        return ResponseEntity.of(personService.update(person));
    }
}
