package com.pawelbugiel.foodtoeat.security.dtos;

import com.pawelbugiel.foodtoeat.security.models.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(
        Long id,
        String email,
        Set<Role> roles
) {}