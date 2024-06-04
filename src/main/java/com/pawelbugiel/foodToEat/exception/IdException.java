package com.pawelbugiel.foodToEat.exception;

public class IdException extends RuntimeException {

    public IdException(String id) {
        super("Wrong id format : " + id);
    }

}
