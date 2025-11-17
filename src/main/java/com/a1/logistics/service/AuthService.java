package com.a1.logistics.service;

import com.a1.logistics.dto.auth.LoginRequest;
import com.a1.logistics.dto.auth.ProfileResponse;
import com.a1.logistics.dto.auth.RefreshTokenRequest;
import com.a1.logistics.dto.auth.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest request);
    TokenResponse refresh(RefreshTokenRequest request);
    ProfileResponse profile(String email);
}
