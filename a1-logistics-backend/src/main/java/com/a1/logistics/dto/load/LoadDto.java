package com.a1.logistics.dto.load;

import com.a1.logistics.entity.LoadStatus;

import java.time.Instant;

public record LoadDto(Long id,
                      String customer,
                      String pickupAddress,
                      Instant pickupTime,
                      String deliveryAddress,
                      Instant deliveryTime,
                      LoadStatus status,
                      Double distance,
                      Double rate,
                      boolean isAmazon,
                      Long assignedDriverId,
                      Long assignedTruckId) {
}
