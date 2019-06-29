package ua.in.lbn.sb2.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.service.PersonService;

@RestController
@RequestMapping("persons")
public class PersonRest {

    private final PersonService personService;

    public PersonRest(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> all() {
        return ResponseEntity.of(Optional.of(personService.all()));
    }

}
