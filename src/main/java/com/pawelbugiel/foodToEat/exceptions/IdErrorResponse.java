package com.pawelbugiel.foodToEat.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class IdErrorResponse {

    private int status;
    private String message;
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public IdErrorResponse() {
        this.localDateTime = LocalDateTime.now();
        this.localDateTime.format(dateTimeFormatter);
    }

    public IdErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
        this.localDateTime.format(dateTimeFormatter);
    }
}
