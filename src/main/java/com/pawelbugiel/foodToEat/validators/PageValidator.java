package com.pawelbugiel.foodToEat.validators;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PageValidator {

    public static final int MIN_PAGE = 0;

    public int getValidPage(String obj) {
        Optional<Integer> integer = checkIfIsInteger(obj);
        Integer result = integer.orElse(-1);
        return usePageValueOrDefault(result);
    }

    public Optional<Integer> checkIfIsInteger(String obj) {
        try {
            return Optional.of(Integer.valueOf(obj));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // what if a value of page is above possible range ?
    public int usePageValueOrDefault(Integer page) {
        return (page >= MIN_PAGE) ? page : MIN_PAGE;
    }

}
