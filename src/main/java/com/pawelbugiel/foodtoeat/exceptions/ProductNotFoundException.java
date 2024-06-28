package com.pawelbugiel.foodtoeat.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String id){
        super("No product with id : " + id);
    }
}
