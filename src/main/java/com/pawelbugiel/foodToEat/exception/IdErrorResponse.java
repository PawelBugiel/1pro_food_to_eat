package com.pawelbugiel.foodToEat.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record IdErrorResponse(String status, String message, String dateTime) {

    public static IdErrorResponse create(String status, String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = localDateTime.format(formatter);
        return new IdErrorResponse(status, message, dateTime);
    }
}
