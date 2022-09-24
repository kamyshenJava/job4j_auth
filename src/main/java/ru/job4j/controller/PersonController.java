package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Person;
import ru.job4j.service.Operation;
import ru.job4j.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable @Valid int id) {
        Optional<Person> person = personService.findById(id);
        checkIfPersonFound(person);
        return new ResponseEntity<>(
                person.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        String password = person.getPassword();
        checkPasswordLength(password);
        return new ResponseEntity<>(
                personService.save(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody @Valid Person person) {
        int id = person.getId();
        Optional<Person> personFound = personService.findById(id);
        checkIfPersonFound(personFound);
        String password = person.getPassword();
        checkPasswordLength(password);
        personService.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid int id) {
        Optional<Person> personFound = personService.findById(id);
        checkIfPersonFound(personFound);
        Person person = new Person();
        person.setId(id);
        this.personService.delete(person);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Person> patch(@RequestBody @Valid Map<String, String> body) {
        String id = body.get("id");
        Optional<Person> person = personService.findById(Integer.parseInt(id));
        checkIfPersonFound(person);
        Person rsl = person.get();
        if (body.containsKey("username")) {
            rsl.setUsername(body.get("username"));
        }
        if (body.containsKey("password")) {
            String password = body.get("password");
            checkPasswordLength(password);
            rsl.setPassword(password);
        }
        personService.save(rsl);
        return ResponseEntity.ok(rsl);
    }

    private void checkPasswordLength(String password) {
        if (password.length() < 6 || password.length() > 20) {
            throw new IllegalArgumentException("Password length should be between 6 and 20 characters.");
        }
    }

    private void checkIfPersonFound(Optional<Person> person) {
        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person with this id has not been found.");
        }
    }

}
