package ru.job4j.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder encoder;

    public PersonService(PersonRepository personRepository, BCryptPasswordEncoder encoder) {
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    public Person save(Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
