package com.pawelbugiel.wastenofood.exceptions;

public class SortingException extends RuntimeException {

    public SortingException(String message) {
        super(message);
    }

    public SortingException(String message, Throwable cause) {
        super(message, cause);
    }
}
