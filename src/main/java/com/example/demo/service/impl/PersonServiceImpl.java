package com.example.demo.service.impl;

import com.example.demo.dto.PersonDto;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.mapper.PersonMapperManual;
import com.example.demo.repository.PersonReposiotory;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapperManual personMapper = new PersonMapperManual();
    private final PersonReposiotory personReposiotory;

    @Override
    public PersonDto findById(long id) {
        return personMapper.convertToDto(
                personReposiotory.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        return personMapper.convertToDto(
                personReposiotory.save(personMapper.converToEntity(personDto)));
    }

    @Override
    public List<PersonDto> findAll() {
        return personMapper.convertToDtoList(personReposiotory.findAll());
    }
}
