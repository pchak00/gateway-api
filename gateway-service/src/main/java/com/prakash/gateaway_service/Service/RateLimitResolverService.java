package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Entity.RouteLimit;
import com.prakash.gateaway_service.Repository.RouteLimitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RateLimitResolverService {

    private final RouteLimitRepository routeLimitRepository;

    public RateLimitResolverService(RouteLimitRepository routeLimitRepository) {
        this.routeLimitRepository = routeLimitRepository;
    }

    public int resolveLimit(Client client, String path) {

        Optional<RouteLimit> routeLimitOpt =
                routeLimitRepository.findByPlanIdAndRoutePattern(
                        client.getPlan().getId(),
                        path
                );

        if (routeLimitOpt.isPresent()) {
            return routeLimitOpt.get().getRequestsPerMinute();
        }

        return client.getPlan().getRequestsPerMinute();
    }
}