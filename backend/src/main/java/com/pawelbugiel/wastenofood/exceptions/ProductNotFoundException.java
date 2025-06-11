package com.pawelbugiel.wastenofood.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(UUID id){
        super("No product with id : " + id.toString());
    }
}
