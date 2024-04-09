package com.example.syncmeet.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdMismatchException extends IllegalArgumentException{
    public IdMismatchException(String message) {super(message);}
}
