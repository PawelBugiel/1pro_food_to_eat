package com.pawelbugiel.foodToEat.validators;

import org.springframework.data.domain.Sort;

public class SortDirectionValidator {

    public static Sort.Direction validDirection(Sort.Direction direction) {
        return (direction == null)
                ? Sort.Direction.ASC
                : direction;
    }
}

