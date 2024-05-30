package com.pawelbugiel.foodToEat.validators;

//@Component
public class SortByValidator {

    public static String valid(ProductProperties properties) {
       return (properties == null)
               ? ProductProperties.EXPIRY_DATE.getValue()
               : properties.getValue();
    }
}
