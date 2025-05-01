package com.pawelbugiel.foodtoeat.security.dtos;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(
        Long id,
        String email,
        Set<String> roles
) {}