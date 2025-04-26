package com.pawelbugiel.foodtoeat.controllers;

import com.pawelbugiel.foodtoeat.security.AuthRequest;
import com.pawelbugiel.foodtoeat.security.AuthResponse;
import com.pawelbugiel.foodtoeat.security.RegisterRequest;
import com.pawelbugiel.foodtoeat.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
