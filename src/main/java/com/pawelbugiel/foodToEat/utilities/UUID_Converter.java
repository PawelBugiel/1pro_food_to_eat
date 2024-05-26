package com.pawelbugiel.foodToEat.utilities;

import com.pawelbugiel.foodToEat.exceptions.IdException;

import java.util.UUID;

public class UUID_Converter {

    public static UUID convertStringToUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IdException(id);
        }
    }
}
