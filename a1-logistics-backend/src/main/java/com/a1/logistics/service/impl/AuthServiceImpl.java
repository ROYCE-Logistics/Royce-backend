package com.a1.logistics.service.impl;

import com.a1.logistics.dto.auth.LoginRequest;
import com.a1.logistics.dto.auth.ProfileResponse;
import com.a1.logistics.dto.auth.RefreshTokenRequest;
import com.a1.logistics.dto.auth.TokenResponse;
import com.a1.logistics.entity.User;
import com.a1.logistics.repository.UserRepository;
import com.a1.logistics.security.JwtService;
import com.a1.logistics.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           UserDetailsService userDetailsService,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        return new TokenResponse(jwtService.generateToken(userDetails), jwtService.generateRefreshToken(userDetails));
    }

    @Override
    public TokenResponse refresh(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.refreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!jwtService.isTokenValid(request.refreshToken(), userDetails)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }
        return new TokenResponse(jwtService.generateToken(userDetails), request.refreshToken());
    }

    @Override
    public ProfileResponse profile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new ProfileResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
