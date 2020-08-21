package com.example.demo.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(long id) {
        super(getFormat(id));
    }

    private static String getFormat(long id) {
        return String.format("Person by id %d not found", id);
    }
}
