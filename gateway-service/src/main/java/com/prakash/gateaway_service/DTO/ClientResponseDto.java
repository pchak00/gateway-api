package com.prakash.gateaway_service.DTO;

public record ClientResponseDto(
        String clientName,
        String apiKey,
        Boolean active,
        String planName
) {
}
