package com.pawelbugiel.foodToEat.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String id){
        super("No product with id : " + id);
    }
}
