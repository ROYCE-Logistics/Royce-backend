package com.a1.logistics.dto.auth;

import com.a1.logistics.entity.UserRole;

public record ProfileResponse(Long id, String name, String email, UserRole role) {
}
