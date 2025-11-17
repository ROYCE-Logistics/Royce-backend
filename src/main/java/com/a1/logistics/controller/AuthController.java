package com.a1.logistics.controller;

import com.a1.logistics.dto.auth.LoginRequest;
import com.a1.logistics.dto.auth.ProfileResponse;
import com.a1.logistics.dto.auth.RefreshTokenRequest;
import com.a1.logistics.dto.auth.TokenResponse;
import com.a1.logistics.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refresh(request);
    }

    @GetMapping("/profile")
    public ProfileResponse profile(@AuthenticationPrincipal UserDetails userDetails) {
        return authService.profile(userDetails.getUsername());
    }
}
