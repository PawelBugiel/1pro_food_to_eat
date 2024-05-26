package com.pawelbugiel.foodToEat.handlers;

import com.pawelbugiel.foodToEat.exceptions.IdException;
import com.pawelbugiel.foodToEat.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String idExceptionHandler(IdException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFoundExceptionHandler(ProductNotFoundException ex) {
        return (ex.getMessage());
    }

}
