package com.example.demo.mapper;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapperManual extends AbsrtactMapper<Person, PersonDto> {

    @Override
    public PersonDto convertToDto(Person person) {
        return PersonDto.builder()
            .id(person.getId())
            .name(person.getName())
            .surname(person.getSurname())
            .build();
    }

    @Override
    public Person converToEntity(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .surname(personDto.getSurname())
                .build();
    }

    @Override
    public List<PersonDto> convertToDtoList(List<Person> personList) {
        return super.convertToDtoList(personList);
    }
}
