package com.a1.logistics.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError(Instant timestamp, HttpStatus status, String message, List<String> details) {
}
