package com.example.syncmeet.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends jakarta.persistence.EntityNotFoundException {

    public EntityNotFoundException(String message) { super(message);}
}
