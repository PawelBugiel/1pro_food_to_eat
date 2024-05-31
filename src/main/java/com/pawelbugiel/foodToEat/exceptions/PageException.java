package com.pawelbugiel.foodToEat.exceptions;

public class PageException extends RuntimeException{
    public PageException() {
    }

    public PageException(String pageNumber) {
        super("Page "  + pageNumber + " is out of bounds.");
    }


}

