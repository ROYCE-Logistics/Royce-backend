package com.a1.logistics.dto.user;

import com.a1.logistics.entity.UserRole;
import com.a1.logistics.entity.UserStatus;

public record UserDto(Long id, String name, String email, String phone, UserRole role, UserStatus status) {
}
