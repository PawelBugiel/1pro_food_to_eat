package com.pawelbugiel.foodToEat.exceptions;

public class IdException extends RuntimeException {

    public IdException() {
    }

    public IdException(String message) {
        super(message);
    }

    public IdException(Throwable cause) {
        super(cause);
    }

    public IdException(String message, Throwable cause) {
        super(message, cause);
    }


}
