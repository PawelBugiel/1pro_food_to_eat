package com.pawelbugiel.foodToEat.handlers;

import com.pawelbugiel.foodToEat.exceptions.IdErrorResponse;
import com.pawelbugiel.foodToEat.exceptions.IdException;
import com.pawelbugiel.foodToEat.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdException.class)
    public ResponseEntity<IdErrorResponse> handleException(IdException exception) {

        IdErrorResponse idErrorResponse = new IdErrorResponse();
        idErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        idErrorResponse.setMessage("Invalid Id passed");
        System.out.println(idErrorResponse.getLocalDateTime() + "888888888888888888888888");

        return new ResponseEntity<>(idErrorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleException(ProductNotFoundException productNotFoundException) {
        return ResponseEntity
                .badRequest()
                .body(productNotFoundException.getClass().getSimpleName() + " no such product");
    }
}
