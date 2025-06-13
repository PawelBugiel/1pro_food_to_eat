package com.pawelbugiel.wastenofood.validators;

import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {
    private final Validator validator;

    public ObjectValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(T objectToValidate) {

        if (objectToValidate == null) {
            throw new ValidationException("Object to validate cannot be null");
        }

        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

        if (!violations.isEmpty()) {
            var errorMessages = violations
                    .stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.toSet());

            throw new ValidationException("Validation failed: " + errorMessages);
        }
    }
}
