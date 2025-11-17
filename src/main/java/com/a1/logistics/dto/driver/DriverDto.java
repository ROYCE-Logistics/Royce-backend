package com.a1.logistics.dto.driver;

import com.a1.logistics.entity.DriverStatus;

public record DriverDto(Long id,
                        Long userId,
                        String driverName,
                        String licenseNumber,
                        Long truckId,
                        Long trailerId,
                        DriverStatus status,
                        Double safetyScore,
                        boolean onlineFlag) {
}
