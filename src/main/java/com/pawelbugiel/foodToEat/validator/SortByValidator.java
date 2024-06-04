package com.pawelbugiel.foodToEat.validator;

import com.pawelbugiel.foodToEat.model.ProductProperties;

//@Component
public class SortByValidator {

    public static String valid(ProductProperties properties) {
       return (properties == null)
               ? ProductProperties.EXPIRY_DATE.getValue()
               : properties.getValue();
    }
}
