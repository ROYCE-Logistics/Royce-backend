package com.a1.logistics.dto.trailer;

import com.a1.logistics.entity.TrailerStatus;
import com.a1.logistics.entity.TrailerType;

public record TrailerDto(Long id, String plateNumber, TrailerStatus status, TrailerType type) {
}
