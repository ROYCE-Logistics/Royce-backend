package com.a1.logistics.dto.alert;

import com.a1.logistics.entity.AlertSeverity;
import com.a1.logistics.entity.AlertType;

import java.time.Instant;

public record AlertDto(Long id,
                       AlertType type,
                       AlertSeverity severity,
                       String message,
                       Long driverId,
                       Long truckId,
                       Instant createdAt,
                       boolean readFlag) {
}
