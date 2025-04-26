package com.pawelbugiel.foodtoeat.security;

import com.pawelbugiel.foodtoeat.models.Role;
import com.pawelbugiel.foodtoeat.models.User;
import com.pawelbugiel.foodtoeat.repositories.RoleRepository;
import com.pawelbugiel.foodtoeat.repositories.UserRepository;
import com.pawelbugiel.foodtoeat.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        Role enduserRole = roleRepository.findByName("ROLE_ENDUSER")
                .orElseThrow(() -> new IllegalStateException("Default role not found: ROLE_ENDUSER"));

        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(Set.of(enduserRole));

        User savedUser = userRepository.save(user);
        String jwt = jwtService.generateToken(new CustomUserDetails(savedUser));
        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.email()));

        UserDetails userDetails = new CustomUserDetails(user);
        String jwt = jwtService.generateToken(userDetails);
        return new AuthResponse(jwt);
    }
}