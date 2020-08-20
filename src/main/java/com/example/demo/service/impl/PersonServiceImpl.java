package com.example.demo.service.impl;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonDto;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.mapper.PersonMapperManual;
import com.example.demo.repository.PersonReposiotory;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    //private final PersonMapper personMapper;
    private final PersonMapperManual personMapper;
    private final PersonReposiotory personReposiotory;

    @Override
    public PersonDto findById(long id) {
        return personMapper.convertToDto(
                personReposiotory.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }

    @Override
    public Person save(Person person) {
        return personReposiotory.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personReposiotory.findAll();
    }
}
