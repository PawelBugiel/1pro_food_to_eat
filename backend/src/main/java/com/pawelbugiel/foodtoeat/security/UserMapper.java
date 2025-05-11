package com.pawelbugiel.foodtoeat.security;

import com.pawelbugiel.foodtoeat.security.dtos.UserResponse;
import com.pawelbugiel.foodtoeat.security.models.Role;
import com.pawelbugiel.foodtoeat.security.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToPrimaryRole")
    UserResponse toUserResponse(User user);

    @Named("mapRolesToPrimaryRole")
    default Set<Role> mapRolesToPrimaryRole(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return Set.of(); // Zwraca pusty Set, jeśli brak ról
        }
        // Priorytetowo traktuj ROLE_ADMIN, w przeciwnym razie ROLE_ENDUSER
        return roles.stream()
                .filter(role -> "ROLE_ADMIN".equals(role.getName()))
                .findFirst()
                .map(role -> Set.of(role))
                .orElseGet(() -> roles.stream()
                        .filter(role -> "ROLE_ENDUSER".equals(role.getName()))
                        .findFirst()
                        .map(role -> Set.of(role))
                        .orElse(Set.of()));
    }
}