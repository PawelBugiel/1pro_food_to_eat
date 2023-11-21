package com.pawelbugiel.foodToEat.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

class UUID_ConverterTest {

    private static final UUID uuid = UUID.randomUUID();
    private static final String stringUUID = uuid.toString();

    @Test
    @DisplayName("convert a String to an UUID with success")
    void testConvertStringToUUID_whenStringIsValid_returnsUUID() {
        //GIVEN
        // WHEN
        Optional<UUID> optionalUUID = UUID_Converter.convertStringToUUID(stringUUID);

        // THEN
        Assertions.assertTrue(optionalUUID.isPresent());
        Assertions.assertEquals(optionalUUID.get().toString(), stringUUID);
    }

    @Test
    @DisplayName("unable to convert a String to an UUID")
    void testConvertStringToUUID_whenPassInvalidString_returnsEmptyOptional() {
        //GIVEN
        UUID uuid = UUID.randomUUID();
        String invalidStringUUID = stringUUID + "©";
        System.out.println(invalidStringUUID);

        // WHEN
        Optional<UUID> optionalUUID = UUID_Converter.convertStringToUUID(invalidStringUUID);

        // THEN
        Assertions.assertTrue(optionalUUID.isEmpty());
    }

    @Test
    @DisplayName("unable to convert a String to an UUID")
    void testConvertStringToUUID_whenStringIsNull_returnsEmptyOptional() {
        //GIVEN
        String nullString = null;

        // WHEN
        Optional<UUID> optionalUUID = UUID_Converter.convertStringToUUID(nullString);

        // THEN
        Assertions.assertTrue(optionalUUID.isEmpty());
    }
}