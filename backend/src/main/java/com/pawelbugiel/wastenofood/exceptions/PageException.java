package com.pawelbugiel.wastenofood.exceptions;

public class PageException extends RuntimeException{
    public PageException() {
    }

    public PageException(String pageNumber) {
        super("Page "  + pageNumber + " is out of bounds.");
    }


}

