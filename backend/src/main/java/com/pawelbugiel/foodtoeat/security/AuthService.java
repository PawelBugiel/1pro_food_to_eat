package com.pawelbugiel.foodtoeat.security;

import com.pawelbugiel.foodtoeat.security.dtos.AuthRequest;
import com.pawelbugiel.foodtoeat.security.dtos.AuthResponse;
import com.pawelbugiel.foodtoeat.security.dtos.RegisterRequest;
import com.pawelbugiel.foodtoeat.security.dtos.UserResponse;
import com.pawelbugiel.foodtoeat.security.jwt.JwtService;
import com.pawelbugiel.foodtoeat.security.models.Role;
import com.pawelbugiel.foodtoeat.security.models.User;
import com.pawelbugiel.foodtoeat.security.repositories.RoleRepository;
import com.pawelbugiel.foodtoeat.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse registerEnduser(RegisterRequest request) {
        Role enduserRole = roleRepository.findByName("ROLE_ENDUSER")
                .orElseThrow(() -> new IllegalStateException("Default role not found: ROLE_ENDUSER"));
        return createAndSaveUser(request, enduserRole);
    }

    public AuthResponse registerAdmin(RegisterRequest request) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("Default role not found: ROLE_ADMIN"));
        return createAndSaveUser(request, adminRole);
    }

    private AuthResponse createAndSaveUser(RegisterRequest request, Role role) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(role));

        User savedUser = userRepository.save(user);
        String jwt = jwtService.generateToken(new CustomUserDetails(savedUser));
        return new AuthResponse(jwt);
    }


    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        UserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtService.generateToken(userDetails);
        return new AuthResponse(jwt);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
        userRepository.delete(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toSet()))
                .build();
    }
}