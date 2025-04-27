package com.pawelbugiel.foodtoeat.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class ProductResponse {

    private UUID id;

    @NotBlank(message = "Product name cannot be empty")
    private final String name;

    @NotNull
    @Min(value = 1, message = "Product quantity must be at least 1")
    private final Integer quantity;

    @NotNull(message = "Expiry date cannot be null")
    @FutureOrPresent(message = "Expiry date must be today or in the future")
    private final LocalDate expiryDate;

}
