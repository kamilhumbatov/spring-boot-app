package com.example.demo.service.impl;

import com.example.demo.dto.PersonDto;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.mapper.PersonMapperManual;
import com.example.demo.repository.PersonReposiotory;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Cacheable
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private static final String CACHE_VALUE = "persons";
    private static final String FIND_ALL_PERSONS = "Find all persons";
    private static final String UPDATE_OR_SAVE_PERSON = "Update or save person";
    private static final String DELETE_PERSON_BY_ID = "Delete person by id {}";
    private static final String GET_PERSON_WITH_ID = "Get person by id {}";

    private final PersonMapperManual personMapper = new PersonMapperManual();
    private final PersonReposiotory personReposiotory;

    @Override
    @Cacheable(value = CACHE_VALUE, key = "#id")
    public PersonDto findById(long id) {
        log.info(GET_PERSON_WITH_ID, id);
        return personMapper.convertToDto(
                personReposiotory.findById(id).orElseThrow(() -> new PersonNotFoundException(id)));
    }

    @Override
    @Transactional
    @CachePut(value = CACHE_VALUE)
    public PersonDto save(PersonDto personDto) {
        log.info(UPDATE_OR_SAVE_PERSON);
        return personMapper.convertToDto(
                personReposiotory.save(personMapper.converToEntity(personDto)));
    }

    @Override
    @Cacheable(value = CACHE_VALUE)
    public List<PersonDto> findAll() {
        log.info(FIND_ALL_PERSONS);
        return personMapper.convertToDtoList(personReposiotory.findAll());
    }

    @Override
    @CacheEvict(value = CACHE_VALUE, key = "#id")
    public void deleteById(long id) {
        log.info(DELETE_PERSON_BY_ID, id);
        personReposiotory.deleteById(id);
    }
}
