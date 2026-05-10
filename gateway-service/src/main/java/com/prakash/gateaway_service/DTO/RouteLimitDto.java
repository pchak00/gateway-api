package com.prakash.gateaway_service.DTO;

public record RouteLimitDto(String routePattern,
                            Integer requestsPerMinute,
                            String planName) {
}
