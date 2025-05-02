package com.pawelbugiel.foodtoeat.security;

import com.pawelbugiel.foodtoeat.security.dtos.UserResponse;
import com.pawelbugiel.foodtoeat.security.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
