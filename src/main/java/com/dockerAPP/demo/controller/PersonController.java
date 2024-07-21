package com.dockerAPP.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dockerAPP.demo.model.Person;
import com.dockerAPP.demo.repository.PersonRepository;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setBirthDate(personDetails.getBirthDate());
            return personRepository.save(person);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @GetMapping("/statistics/variance")
    public double getAgeVariance() {
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty()) {
            return 0;
        }

        double average = persons.stream()
                .mapToInt(person -> person.getBirthDate().until(LocalDate.now()).getYears())
                .average()
                .orElse(0);

        double variance = persons.stream()
                .mapToDouble(person -> Math.pow(person.getBirthDate().until(LocalDate.now()).getYears() - average, 2))
                .average()
                .orElse(0);

        return variance;
    }
}
