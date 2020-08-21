package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class ErrorDto {

    private int code;
    private String message;
    private Calendar timestamp;
}

