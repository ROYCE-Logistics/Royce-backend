package com.a1.logistics.dto.alert;

import com.a1.logistics.entity.AlertSeverity;
import com.a1.logistics.entity.AlertType;
import jakarta.validation.constraints.NotBlank;

public record AlertRequest(AlertType type,
                           AlertSeverity severity,
                           @NotBlank String message,
                           Long driverId,
                           Long truckId) {
}
