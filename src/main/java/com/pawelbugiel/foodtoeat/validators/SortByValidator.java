package com.pawelbugiel.foodtoeat.validators;

import com.pawelbugiel.foodtoeat.models.ProductProperties;

//@Component
public class SortByValidator {

    public static String valid(ProductProperties properties) {
       return (properties == null)
               ? ProductProperties.EXPIRY_DATE.getValue()
               : properties.getValue();
    }
}
