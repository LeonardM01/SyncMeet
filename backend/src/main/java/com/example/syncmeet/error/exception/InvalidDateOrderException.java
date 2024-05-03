package com.example.syncmeet.error.exception;

import java.time.DateTimeException;

public class InvalidDateOrderException extends DateTimeException {
    public InvalidDateOrderException(String message) {super(message);}
}
