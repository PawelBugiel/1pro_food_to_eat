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
    @Size(min = 3, max = 33, message = "The name must be between 3 and 33 characters long")
    private String name;


    @Range(min = 1, max = 10_000,  message = "Product quantity must be in range from 1 to 10 000")
    private int quantity;

    @FutureOrPresent
    private LocalDate expiryDate;


}
