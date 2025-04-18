package com.pawelbugiel.foodtoeat.services;

import com.pawelbugiel.foodtoeat.dtos.EndUserDTO;
import com.pawelbugiel.foodtoeat.models.EndUser;
import com.pawelbugiel.foodtoeat.models.Role;
import com.pawelbugiel.foodtoeat.repositories.EndUserRepository;
import com.pawelbugiel.foodtoeat.repositories.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EndUserService {
    private final EndUserRepository endUserRepository;
    private final RoleRepository roleRepository;

    public EndUserService(EndUserRepository endUserRepository, RoleRepository roleRepository) {
        this.endUserRepository = endUserRepository;
        this.roleRepository = roleRepository;
    }

    public EndUser createUser(@Valid EndUserDTO endUserDTO) {
        // Check if email already exists
        if (endUserRepository.findByEmail(endUserDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create a new user
        EndUser endUser = new EndUser();
        endUser.setPassword(endUserDTO.getPassword()); // TODO Hashing password
        endUser.setEmail(endUserDTO.getEmail());

        // Assign roles
        Set<Role> roles = new HashSet<>();
        for (String roleName : endUserDTO.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " not found"));
            roles.add(role);
        }
        endUser.setRoles(roles);

        return endUserRepository.save(endUser);
    }
}
