package com.pawelbugiel.foodtoeat.exceptions;

public class IdException extends RuntimeException {

    public IdException(String id) {
        super("Wrong id format : " + id);
    }

}
