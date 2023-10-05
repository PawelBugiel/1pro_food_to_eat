package com.pawelbugiel.foodToEat.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException illegalStateException) {
        return ResponseEntity
                .badRequest()
                .body(illegalStateException.getClass().getSimpleName() + " .. ise exception ..");
    }
}
