package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
//@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonRestController {

    private static final String API_ID = "/{id}";
    private static final String API_SAVE = "/save";
    private static final String API_FINDALL = "/findAll";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";

    private final PersonService personService;

    @GetMapping(API_ID)
    public PersonDto findPersonById(
            @PathVariable @Positive(message = ID_MUST_BE_POSITIVE) long id) {
        return personService.findById(id);
    }

    @PostMapping(API_SAVE)
    public PersonDto savePerson(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @GetMapping(API_FINDALL)
    public List<PersonDto> findAllPerson() {
        return personService.findAll();
    }

}
