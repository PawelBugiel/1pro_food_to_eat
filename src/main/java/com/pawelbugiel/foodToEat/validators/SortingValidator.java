package com.pawelbugiel.foodToEat.validators;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortingValidator {

    public static final Sort.Direction ASC_SORTING = Sort.Direction.ASC;
    public static final Sort.Direction DESC_SORTING = Sort.Direction.ASC;

    public  Sort.Direction validSortingType(Sort.Direction sort) {
        return (sort == null) ? ASC_SORTING : sort;
    }

    public  Sort.Direction setDescending() {
        return DESC_SORTING;
    }
}
