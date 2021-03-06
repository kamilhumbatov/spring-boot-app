package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResponseModel<T> {
    public static final String SUCCESS = "success";

    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private ResponseModel(T data) {
        this.code = HttpStatus.OK.value();
        this.data = data;
        this.message = SUCCESS;
        this.timestamp = LocalDateTime.now();
    }

    private ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ResponseModel ok(T t) {
        return new ResponseModel(t);
    }

    public static <T>ResponseModel badRequest(int code, String message){
        return new ResponseModel(code,message);
    }
}
