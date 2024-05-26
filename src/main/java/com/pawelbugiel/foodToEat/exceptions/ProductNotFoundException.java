package com.pawelbugiel.foodToEat.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(UUID id){
        super("No product with id : " + id);
    }
}
