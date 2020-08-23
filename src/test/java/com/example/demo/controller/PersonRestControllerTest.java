package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.service.impl.PersonServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.isA;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonRestController.class)
public class PersonRestControllerTest {

    private PersonDto personDto;
    private static final long ID = 1L;
    private static final String NAME = "Kamil";
    private static final String SURNAME = "Humbatov";
    private static final String API_GET = "/person/{id}";
    private static final String API_SAVE = "/person/save";
    private static final String API_FIND_ALL = "/person/findAll";
    private static final String API_DELETE_ID = "/person/delete/{id}";

    public static final String SUCCESS = "success";
    private static final String ERROR_CODE = "$.code";
    private static final String ERROR_MESSAGE = "$.message";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PersonServiceImpl personService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        personDto = PersonDto.builder().id(ID).name(NAME).surname(SURNAME).build();
    }

    @Test
    public void givenIdThenOk() throws Exception {
        when(personService.findById(anyLong())).thenReturn(personDto);

        mvc.perform(get(API_GET, ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenIdThenPersonNotFoundExeption() throws Exception {
        when(personService.findById(anyLong())).thenThrow(new PersonNotFoundException(ID));

        mvc.perform(get(API_GET, ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(ERROR_CODE, is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath(ERROR_MESSAGE, is(String.format("Person by id %d not found", ID))));
    }

    @Test
    public void givenPersonSaveOk() throws Exception {
        when(personService.save(personDto)).thenReturn(personDto);

        mvc.perform(post(API_SAVE)
                .content(asJsonString(personDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(personDto.getId()));
    }

    @Test
    public void deletePersonIsOk() throws Exception {
        mvc.perform(delete(API_DELETE_ID, ID))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPersonsOk() throws Exception {
        when(personService.findAll()).thenReturn(Collections.singletonList(personDto));

        mvc.perform(get(API_FIND_ALL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}
