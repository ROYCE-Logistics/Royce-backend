package com.a1.logistics.dto.truck;

import com.a1.logistics.entity.TruckStatus;

import java.time.LocalDate;

public record TruckDto(Long id,
                       String plateNumber,
                       String vin,
                       TruckStatus status,
                       Long mileage,
                       LocalDate lastMaintenanceDate,
                       LocalDate insuranceExpiry,
                       String notes) {
}
