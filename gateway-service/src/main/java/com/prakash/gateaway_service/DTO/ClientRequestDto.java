package com.prakash.gateaway_service.DTO;

public record ClientRequestDto(String name,
                               Integer requestsPerMinute,
                               Boolean active) {
}
