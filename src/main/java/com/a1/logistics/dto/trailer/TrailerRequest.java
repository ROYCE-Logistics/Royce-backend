package com.a1.logistics.dto.trailer;

import com.a1.logistics.entity.TrailerStatus;
import com.a1.logistics.entity.TrailerType;
import jakarta.validation.constraints.NotBlank;

public record TrailerRequest(@NotBlank String plateNumber, TrailerStatus status, TrailerType type) {
}
