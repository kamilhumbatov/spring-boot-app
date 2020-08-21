package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.dto.PersonDto;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.repository.PersonReposiotory;
import com.example.demo.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonServiceImpl.class)
@ContextConfiguration(classes = {PersonServiceImpl.class})
public class PersonServiceImplTest {

    private Person person;
    private PersonDto personDto;
    private static final long ID = 1L;
    private static final String NAME = "Kamil";
    private static final String SURNAME = "Humbatov";

    @MockBean
    private PersonReposiotory personReposiotory;

    @Autowired
    private PersonServiceImpl personService;

    @Before
    public void setUp() {
        person = Person.builder().id(ID).name(NAME).surname(SURNAME).build();
        personDto = PersonDto.builder().id(ID).name(NAME).surname(SURNAME).build();
    }

    @Test
    public void findById() {
        when(personReposiotory.findById(anyLong())).thenReturn(Optional.of(person));

        assertThat(personService.findById(anyLong()).getName()).isEqualTo(NAME);
        verify(personReposiotory, times(1)).findById(anyLong());
    }

    @Test
    public void findByIdPersonNotFoundExeption() {
        when(personReposiotory.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.findById(ID))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessage(String.format("Person by id %d not found", ID));
        verify(personReposiotory, times(1)).findById(ID);
    }

    @Test
    public void savePerson() {
        Person personNew = Person.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .build();
        PersonDto personDtoNew = PersonDto.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .build();

        when(personReposiotory.save(personNew)).thenReturn(person);

        assertThat(personService.save(personDtoNew).getName()).isEqualTo(person.getName());
        verify(personReposiotory, times(1)).save(personNew);
    }

    @Test
    public void findAll() {
        when(personReposiotory.findAll()).thenReturn(Collections.singletonList(person));

        assertThat(personService.findAll().size()).isEqualTo(1);
        verify(personReposiotory, times(1)).findAll();
    }
}
