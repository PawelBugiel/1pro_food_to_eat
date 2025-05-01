package com.pawelbugiel.foodtoeat.dtos;

import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID id,
        String name,
        Integer quantity,
        LocalDate expiryDate) {
}
