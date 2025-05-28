package com.pawelbugiel.foodtoeat.security;

import com.pawelbugiel.foodtoeat.security.dtos.AuthRequest;
import com.pawelbugiel.foodtoeat.security.dtos.AuthResponse;
import com.pawelbugiel.foodtoeat.security.dtos.RegisterRequest;
import com.pawelbugiel.foodtoeat.security.dtos.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register-enduser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> registerEnduser(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerEnduser(request));
    }

    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> registerAdmin(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        authService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }
}
