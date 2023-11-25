package com.pawelbugiel.foodToEat.validators;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntegerValidator {

    public int getValidPage(String obj) {
        Optional<Integer> integer = checkIfIsInteger(obj);
        Integer result = integer.orElse(-1);
        return usePageValueOrDefault(result);
    }

    public Optional<Integer> checkIfIsInteger(String obj) {
//        if (obj == null) return Optional.empty();
        Optional<Integer> result = Optional.empty();
        try {
            result = Optional.of(Integer.valueOf(obj));
            return result;
        } catch (NumberFormatException e) {
            return result;
        }

//        return Optional.of(Integer.valueOf(obj)).or(Optional::empty);
    }

    public int usePageValueOrDefault(Integer page) {
        return (page >= 0) ? page : 0;
    }

}
