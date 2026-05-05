package com.prakash.gateaway_service.DTO;

import java.time.LocalDateTime;

public record AbuseAlertResponseDto(
        String clientName,
        Integer blockedCount,
        String severity,
        String message,
        LocalDateTime windowStart,
        LocalDateTime createdAt
) {}
