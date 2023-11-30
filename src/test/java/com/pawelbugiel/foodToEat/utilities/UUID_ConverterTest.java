package com.pawelbugiel.foodToEat.utilities;

import com.pawelbugiel.foodToEat.exceptions.IdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

class UUID_ConverterTest {

    private static final UUID uuid = UUID.randomUUID();
    private static final String stringUUID = uuid.toString();
    @Mock
    private static final IdException idException = new IdException("Invalid id passed");

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
    @DisplayName("unable to convert a to long String to UUID")
    void testConvertStringToUUID_whenPassToLongString_throwsIdException() {
        //GIVEN
        String toLongStringUUID = stringUUID + "©";
        // WHEN
        Executable convert = () -> UUID_Converter.convertStringToUUID(toLongStringUUID);
        // THEN
        Assertions.assertThrows(IdException.class, convert);
//        Assertions.assertEquals();
    }

    @Test
    @DisplayName("unable to convert a to short String to UUID")
    void testConvertStringToUUID_whenPassToShort_throwsIdException() {
        //GIVEN
        String toShortStringUUID = "123";
//        Mockito.when(idException.getMessage()).thenReturn("Invalid id passed");
        // WHEN
        Executable execute = () -> UUID_Converter.convertStringToUUID(toShortStringUUID);
        // THEN
        Assertions.assertThrows(IdException.class, execute);
        Assertions.assertEquals("Invalid id passed", idException.getMessage());
    }

    @Test
    @DisplayName("unable to convert null String to UUID")
    void testConvertStringToUUID_whenStringIsNull_returnsEmptyOptional() {
        //GIVEN
        String nullString = null;
        // WHEN
        Optional<UUID> optionalUUID = UUID_Converter.convertStringToUUID(nullString);
        // THEN
        Assertions.assertTrue(optionalUUID.isEmpty());
    }
}