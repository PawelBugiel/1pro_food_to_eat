package com.pawelbugiel.foodtoeat.validators;

import com.pawelbugiel.foodtoeat.exceptions.IdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;


public class UUIDValidatorTest {

    private static final UUID uuid = UUID.randomUUID();
    private static final String STRING_UUID = uuid.toString();

    private static final String DEFAULT_ID_EXCEPTION_MESSAGE = "Wrong id format : ";

    @Test
    @DisplayName("Convert a String to an UUID with success")
    void testConvertStringToUUID_whenStringIsValid_returnsUUID() {
        //GIVEN
        // WHEN
        UUID resultUUID = UUID_Validator.convertStringToUUID(STRING_UUID);

        // THEN
        Assertions.assertEquals(STRING_UUID, resultUUID.toString());
    }

    @Test
    @DisplayName("Unable to convert a too long String to UUID")
    void testConvertStringToUUID_whenPassTooLongString_throwsIdException() {
        //GIVEN
        String tooLongStringUUID = STRING_UUID + "123";
        // WHEN
        Executable conversionResult = () -> UUID_Validator.convertStringToUUID(tooLongStringUUID);
        // THEN
        IdException resultException = Assertions.assertThrows(IdException.class, conversionResult);
        Assertions.assertEquals(DEFAULT_ID_EXCEPTION_MESSAGE + STRING_UUID + "123", resultException.getMessage());
    }

    @Test
    @DisplayName("Unable to convert a too short String to UUID")
    void testConvertStringToUUID_whenPassTooShort_throwsIdException() {
        //GIVEN
        String tooShortStringUUID = "123";
        // WHEN
        Executable execute = () -> UUID_Validator.convertStringToUUID(tooShortStringUUID);
        // THEN
        IdException resultException = Assertions.assertThrows(IdException.class, execute);
        Assertions.assertEquals(DEFAULT_ID_EXCEPTION_MESSAGE + "123", resultException.getMessage());
    }

    @Test
    @DisplayName("Unable to convert null String to UUID")
    void testConvertStringToUUID_whenStringIsNull_throwsIdException() {
        //GIVEN
        // WHEN
        Executable execute = () -> UUID_Validator.convertStringToUUID(null);
        // THEN
        IdException resultException = Assertions.assertThrows(IdException.class, execute);
        Assertions.assertEquals(DEFAULT_ID_EXCEPTION_MESSAGE + "null", resultException.getMessage());
    }

    @Test
    @DisplayName("Unable to convert UUID with not appropriate characters ")
    void testConvertStringToUUID_whenStringHasXCharacter_throwsIdException() {
        //GIVEN
        String invalid_UUID = "X9e2f670-c885-4ae9-b0ac-26bfddc9c8a8";
        //WHEN
        Executable executable = () -> UUID_Validator.convertStringToUUID(invalid_UUID);
        //THEN
        IdException resultException = Assertions.assertThrows(IdException.class, executable);
        Assertions.assertEquals(DEFAULT_ID_EXCEPTION_MESSAGE + invalid_UUID, resultException.getMessage());

    }


}