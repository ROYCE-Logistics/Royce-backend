package com.a1.logistics.dto.settlement;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SettlementRequest(@NotNull Long driverId,
                                @NotNull LocalDate periodStart,
                                @NotNull LocalDate periodEnd) {
}
