package com.pawelbugiel.foodtoeat.validators;

import java.util.Optional;

public class PageValidator {

    public static final int MIN_PAGE = 0;

    public static int getValidPage(String obj) {
        Optional<Integer> integer = checkIfIsInteger(obj);
        Integer result = integer.orElse(-1);
        return usePageValueOrDefault(result);
    }

    public static Optional<Integer> checkIfIsInteger(String obj) {
        try {
            return Optional.of(Integer.valueOf(obj));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // #q what if a value of page is above possible range ?
    private static int usePageValueOrDefault(Integer page) {
        return (page >= MIN_PAGE) ? page : MIN_PAGE;
    }
}
