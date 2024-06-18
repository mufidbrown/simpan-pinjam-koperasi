package com.koperasi.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private final List<String> errors;

    public ValidationException(BindingResult bindingResult) {
        super("Validation error occurred");
        this.errors = bindingResult.getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                    } else {
                        return error.getObjectName() + ": " + error.getDefaultMessage();
                    }
                })
                .collect(Collectors.toList());
    }

    public List<String> getErrors() {
        return errors;
    }

}
