package com.a1.logistics.dto.load;

import com.a1.logistics.entity.LoadStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record LoadRequest(@NotBlank String customer,
                          @NotBlank String pickupAddress,
                          Instant pickupTime,
                          @NotBlank String deliveryAddress,
                          Instant deliveryTime,
                          LoadStatus status,
                          Double distance,
                          Double rate,
                          boolean isAmazon) {
}
