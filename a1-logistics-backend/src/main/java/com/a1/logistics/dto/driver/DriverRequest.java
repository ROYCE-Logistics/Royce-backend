package com.a1.logistics.dto.driver;

import com.a1.logistics.entity.DriverStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DriverRequest(@NotNull Long userId,
                            @NotBlank String licenseNumber,
                            Long truckId,
                            Long trailerId,
                            DriverStatus status,
                            Double safetyScore,
                            boolean onlineFlag) {
}
