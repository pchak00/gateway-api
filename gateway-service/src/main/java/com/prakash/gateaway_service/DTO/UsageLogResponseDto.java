package com.prakash.gateaway_service.DTO;


import java.time.LocalDateTime;

public record UsageLogResponseDto(
        Long id,
        String path,
        String method,
        Boolean allowed,
        Integer statusCode,
        String reason,
        LocalDateTime timestamp
) {}