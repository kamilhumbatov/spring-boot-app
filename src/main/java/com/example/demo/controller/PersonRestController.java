package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.ResponseModel;
import com.example.demo.service.PersonService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonRestController {

    private static final String API_ID = "/{personId}";
    private static final String API_SAVE = "/save";
    private static final String API_FIND_ALL = "/findAll";
    private static final String API_DELETE_BY_ID = "/delete/{personId}";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";
    private static final String FIND_ALL_PERSONS = "Find all persons";
    private static final String UPDATE_OR_SAVE_PERSON = "Update or save person";
    private static final String DELETE_PERSON_BY_ID = "Delete person by id";
    private static final String GET_PERSON_WITH_ID = "Get person by id";

    private final PersonService personService;

    @GetMapping(API_ID)
    @ApiOperation(GET_PERSON_WITH_ID)
    public ResponseModel findPersonById(
            @PathVariable @Positive(message = ID_MUST_BE_POSITIVE) long personId) {
        return ResponseModel.ok(personService.findById(personId));
    }

    @PostMapping(API_SAVE)
    @ApiOperation(UPDATE_OR_SAVE_PERSON)
    public ResponseModel savePerson(@Validated @RequestBody PersonDto personDto) {
        return ResponseModel.ok(personService.save(personDto));
    }

    @GetMapping(API_FIND_ALL)
    @ApiOperation(FIND_ALL_PERSONS)
    public ResponseModel findAllPerson() {
        return ResponseModel.ok(personService.findAll());
    }

    @DeleteMapping(API_DELETE_BY_ID)
    @ApiOperation(DELETE_PERSON_BY_ID)
    public void deleteUserByID(@PathVariable long personId) {
        personService.deleteById(personId);
    }
}
