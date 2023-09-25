package com.pawelbugiel.foodToEat.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProductDto {

    private String name;
    private double quantity;
    private LocalDate expiryDate;

    public ProductDto(String name, double quantity, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
}
