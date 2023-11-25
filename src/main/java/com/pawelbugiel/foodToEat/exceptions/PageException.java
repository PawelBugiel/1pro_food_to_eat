package com.pawelbugiel.foodToEat.exceptions;

public class PageException extends RuntimeException{
    public PageException() {
    }

    public PageException(String message) {
        super(message);
    }

    public PageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageException(Throwable cause) {
        super(cause);
    }
}

