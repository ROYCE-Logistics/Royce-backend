package com.a1.logistics.dto.document;

import com.a1.logistics.entity.DocumentType;

import java.time.Instant;

public record DocumentDto(Long id,
                          DocumentType type,
                          String fileName,
                          String filePath,
                          Long loadId,
                          Long driverId,
                          Instant uploadedAt,
                          Long uploadedBy) {
}
