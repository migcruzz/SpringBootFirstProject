package com.dockerAPP.demo.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockerAPP.demo.model.Person;
import com.dockerAPP.demo.repository.PersonRepository;

@Service
public class DataService {

    @Autowired
    private PersonRepository personRepository;

    private static final List<String> NAMES = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

    @PostConstruct
    public void init() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = NAMES.get(random.nextInt(NAMES.size()));
            LocalDate birthDate = LocalDate.of(1990 + random.nextInt(30), Month.JANUARY, 1);
            personRepository.save(new Person(name, birthDate));
        }
    }
}
