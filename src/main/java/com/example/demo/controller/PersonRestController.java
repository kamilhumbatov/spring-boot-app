package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.ResponseModel;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonRestController {

    private static final String API_ID = "/{id}";
    private static final String API_SAVE = "/save";
    private static final String API_FIND_ALL = "/findAll";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";

    private final PersonService personService;

    @GetMapping(API_ID)
    public ResponseModel findPersonById(
            @PathVariable @Positive(message = ID_MUST_BE_POSITIVE) long id) {
        return new ResponseModel(personService.findById(id));
    }

    @PostMapping(API_SAVE)
    public ResponseModel savePerson(@RequestBody PersonDto personDto) {
        return new ResponseModel(personService.save(personDto));
    }

    @GetMapping(API_FIND_ALL)
    public ResponseModel findAllPerson() {
        return new ResponseModel(personService.findAll());
    }

}
