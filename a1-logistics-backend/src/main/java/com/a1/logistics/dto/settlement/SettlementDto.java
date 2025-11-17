package com.a1.logistics.dto.settlement;

import com.a1.logistics.entity.SettlementStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SettlementDto(Long id,
                            Long driverId,
                            LocalDate periodStart,
                            LocalDate periodEnd,
                            BigDecimal totalAmount,
                            SettlementStatus status) {
}
