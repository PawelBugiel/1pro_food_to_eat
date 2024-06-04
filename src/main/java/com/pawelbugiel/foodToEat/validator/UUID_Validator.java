package com.pawelbugiel.foodToEat.validator;

import com.pawelbugiel.foodToEat.exception.IdException;

import java.util.UUID;

public class UUID_Validator {

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[4][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

    public static UUID convertStringToUUID(String id) {
        String tempId = validUUID(id);
        return UUID.fromString(tempId);
    }

    private static String validUUID(String id ){
        if (id == null || !id.matches(UUID_REGEX)) throw new IdException(id);
        return id;
    }
}
