package com.prakash.gateaway_service.DTO;

public record ClientStatsResponseDto(
        Long clientId,
        long totalRequests,
        long allowedRequests,
        long blockedRequests,
        double blockRate
) {}