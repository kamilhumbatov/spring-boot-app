package com.example.demo.exception;

import com.example.demo.dto.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity customHandleNotFound(PersonNotFoundException ex) {
        log.warn(ex.getMessage());
        ResponseModel responseModel = new ResponseModel(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity(responseModel, HttpStatus.BAD_REQUEST);
    }
}
