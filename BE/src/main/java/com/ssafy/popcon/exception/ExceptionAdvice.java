package com.ssafy.popcon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<String> exception(Exception e){
        System.out.println(e.getMessage());
//        System.out.println(e.getStackTrace()[0]);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getLocalizedMessage());
    }
}
