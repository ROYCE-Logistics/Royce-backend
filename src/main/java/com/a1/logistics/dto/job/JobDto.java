package com.a1.logistics.dto.job;

import com.a1.logistics.entity.JobStatus;

import java.time.Instant;

public record JobDto(Long id,
                     String type,
                     String formatOrSource,
                     JobStatus status,
                     Instant createdAt,
                     Instant finishedAt,
                     String link) {
}
