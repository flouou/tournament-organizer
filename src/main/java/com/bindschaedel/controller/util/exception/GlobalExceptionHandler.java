package com.bindschaedel.controller.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice(basePackages = { "com.bindschaedel.controller" })
public class GlobalExceptionHandler {

    private final ErrorEncoder errorEncoder;

    public GlobalExceptionHandler(ErrorEncoder errorEncoder) {
        this.errorEncoder = errorEncoder;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public Map<String,String> handleValidationExceptions(TransactionSystemException ex) {
        return errorEncoder.encode(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public Map<String,String> handleValidationExceptions(NumberFormatException ex) {
        return errorEncoder.encode(ex);
    }
}
