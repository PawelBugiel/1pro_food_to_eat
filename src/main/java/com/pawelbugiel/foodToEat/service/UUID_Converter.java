package com.pawelbugiel.foodToEat.service;

import java.util.Optional;
import java.util.UUID;

public class UUID_Converter {

    public static Optional<UUID> isValidUUID(String stringUUID) {
        try {
            return Optional.of(UUID.fromString(stringUUID));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
