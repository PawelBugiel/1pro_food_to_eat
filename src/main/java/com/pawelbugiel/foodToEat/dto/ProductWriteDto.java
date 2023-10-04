package com.pawelbugiel.foodToEat.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
public class ProductWriteDto {

    @NotNull(message = "Product name cannot be empty")
    @Size(min = 3, max = 33, message = "A name needs from 3 to 33 characters.")
    private String name;


    @Range(min = 1, max = 10_000,  message = "Quantity must be in range from 1 to 1 000 000")
    private int quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


}
