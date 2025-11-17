package com.a1.logistics.dto.truck;

import com.a1.logistics.entity.TruckStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TruckRequest(@NotBlank String plateNumber,
                           @NotBlank String vin,
                           TruckStatus status,
                           Long mileage,
                           LocalDate lastMaintenanceDate,
                           LocalDate insuranceExpiry,
                           String notes) {
}
