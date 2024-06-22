package com.pawelbugiel.foodToEat.validators;

import com.pawelbugiel.foodToEat.models.ProductProperties;

//@Component
public class SortByValidator {

    public static String valid(ProductProperties properties) {
       return (properties == null)
               ? ProductProperties.EXPIRY_DATE.getValue()
               : properties.getValue();
    }
}
