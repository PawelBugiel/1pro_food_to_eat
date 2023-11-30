package com.pawelbugiel.foodToEat.handlers;

import com.pawelbugiel.foodToEat.exceptions.IdErrorResponse;
import com.pawelbugiel.foodToEat.exceptions.IdException;
import com.pawelbugiel.foodToEat.exceptions.PageException;
import com.pawelbugiel.foodToEat.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdException.class)
    public ResponseEntity<IdErrorResponse> idExceptionHandler(IdException exception) {
        String status = HttpStatus.NOT_FOUND.toString();
        String message = "Invalid Id passed";
        IdErrorResponse idErrorResponse = IdErrorResponse.create(status, message);

        return new ResponseEntity<>(idErrorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundExceptionHandler(ProductNotFoundException productNotFoundException) {
        return ResponseEntity
                .badRequest()
                .body(productNotFoundException.getClass().getSimpleName() + " no such product");
    }

    @ExceptionHandler(PageException.class)
    public ResponseEntity<?> pageNotFoundExceptionHandler(PageException pageException){
        return new ResponseEntity<>(pageException.getClass().getSimpleName() + ". Invalid page value", HttpStatus.BAD_REQUEST);
    }
}
