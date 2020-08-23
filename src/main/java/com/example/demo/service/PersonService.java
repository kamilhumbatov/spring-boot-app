package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto findById(long personId);

    PersonDto save(PersonDto person);

    List<PersonDto> findAll();

    void deleteById(long personId);
}
