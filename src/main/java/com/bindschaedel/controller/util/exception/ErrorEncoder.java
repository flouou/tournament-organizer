package com.bindschaedel.controller.util.exception;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ErrorEncoder {

    protected Map<String, String> encode(TransactionSystemException ex) {
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

    protected Map<String, String> encode(NumberFormatException ex) {
        Map<String,String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }
}
