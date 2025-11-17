package com.a1.logistics.dto.load;

import com.a1.logistics.entity.LoadStatus;

import java.time.Instant;

public record LoadStatusHistoryDto(Long id, LoadStatus oldStatus, LoadStatus newStatus, Instant changedAt, Long changedBy) {
}
