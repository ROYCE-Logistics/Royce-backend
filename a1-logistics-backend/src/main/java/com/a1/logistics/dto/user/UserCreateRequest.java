package com.a1.logistics.dto.user;

import com.a1.logistics.entity.UserRole;
import com.a1.logistics.entity.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(@NotBlank String name,
                                @Email String email,
                                String phone,
                                @NotBlank String password,
                                @NotNull UserRole role,
                                @NotNull UserStatus status) {
}
