package com.pawelbugiel.foodtoeat.validators;

import com.pawelbugiel.foodtoeat.models.ProductProperties;

public class SortByValidator {

    public static String valid(String properties) {
       return (properties == null)
               ? ProductProperties.EXPIRY_DATE.getValue()
//               : properties.getValue();
               : properties;
    }
}
