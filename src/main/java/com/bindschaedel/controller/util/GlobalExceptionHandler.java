package com.bindschaedel.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = { "com.bindschaedel.controller" })
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public Map<String,String> handleValidationExceptions(TransactionSystemException ex) {
        Map<String,String> errors = new HashMap<>();
        if (ex.getOriginalException() != null &&
            ex.getOriginalException().getCause() != null &&
            ex.getOriginalException().getCause() instanceof ConstraintViolationException) {
            ((ConstraintViolationException) ex.getOriginalException().getCause()).getConstraintViolations().forEach((error) -> {
                String fieldName = error.getPropertyPath().toString();
                String errorMessage = error.getMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        else {
            errors.put("error", ex.getLocalizedMessage());
        }
        return errors;
    }
}
