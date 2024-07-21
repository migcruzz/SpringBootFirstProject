package com.dockerAPP.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerAPP.demo.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
