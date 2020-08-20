package com.example.demo.repository;

import com.example.demo.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonReposiotory extends CrudRepository<Person,Long> {
    List<Person> findAll();
}
