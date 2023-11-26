package com.pawelbugiel.foodToEat.utilities;

import com.pawelbugiel.foodToEat.exceptions.IdException;

import java.util.Optional;
import java.util.UUID;

public class UUID_Converter {
    // refactor - to a ternary operator ? or other
    public static Optional<UUID> convertStringToUUID(String stringUUID) {
        if (stringUUID == null) return Optional.empty();
        try {
            return Optional.of(UUID.fromString(stringUUID));
        } catch (IllegalArgumentException e) {
            throw new IdException();
        }
    }
}
