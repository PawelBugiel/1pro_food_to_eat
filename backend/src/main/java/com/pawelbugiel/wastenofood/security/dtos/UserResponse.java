package com.pawelbugiel.wastenofood.security.dtos;

import com.pawelbugiel.wastenofood.security.models.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(
        Long id,
        String email,
        Set<Role> roles
) {}