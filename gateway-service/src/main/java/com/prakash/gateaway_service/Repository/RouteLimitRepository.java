package com.prakash.gateaway_service.Repository;

import com.prakash.gateaway_service.Entity.RouteLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RouteLimitRepository extends JpaRepository<RouteLimit, Long> {
    Optional<RouteLimit> findByPlanIdAndRoutePattern(Long planId, String routePattern);
}
