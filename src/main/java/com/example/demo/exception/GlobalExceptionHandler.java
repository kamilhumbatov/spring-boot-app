package com.example.demo.exception;

import com.example.demo.dto.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseModel customHandleNotFound(PersonNotFoundException ex) {
        log.warn(ex.getMessage());
        return new ResponseModel(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }
}
