package com.a1.logistics.dto.load;

import com.a1.logistics.entity.LoadStatus;
import jakarta.validation.constraints.NotNull;

public record LoadStatusUpdateRequest(@NotNull LoadStatus status) {
}
