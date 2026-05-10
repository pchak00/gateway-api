package com.prakash.gateaway_service.Controller;

import com.prakash.gateaway_service.DTO.*;
import com.prakash.gateaway_service.Entity.Plan;
import com.prakash.gateaway_service.Entity.RouteLimit;
import com.prakash.gateaway_service.Exception.InvalidCredentialsException;
import com.prakash.gateaway_service.Repository.PlanRepository;
import com.prakash.gateaway_service.Repository.RouteLimitRepository;
import com.prakash.gateaway_service.Service.AbuseDetectionService;
import com.prakash.gateaway_service.Service.ClientService;
import com.prakash.gateaway_service.Service.UsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/clients")
public class GatewayController {

    private UsageLogService usageLogService;
    private ClientService clientService;
    private AbuseDetectionService abuseDetectionService;
    private PlanRepository planRepository;
    private RouteLimitRepository routeLimitRepository;

    GatewayController(UsageLogService usageLogService, ClientService clientService, AbuseDetectionService abuseDetectionService, PlanRepository planRepository,  RouteLimitRepository routeLimitRepository) {
        this.usageLogService = usageLogService;
        this.clientService = clientService;
        this.abuseDetectionService = abuseDetectionService;
        this.planRepository = planRepository;
        this.routeLimitRepository = routeLimitRepository;
    }

    @PostMapping
    public ClientResponseDto createClient(@RequestBody ClientRequestDto clientRequest) {
        return clientService.addClient(clientRequest);
    }

    @GetMapping
    public List<ClientResponseDto> showAllClient() {
        return clientService.showAllClients();
    }

    @GetMapping("{clientId}/usage")
    public List<UsageLogResponseDto> findByClientIdOrderByTimestampDesc(@PathVariable Long clientId) {
        return usageLogService.getUsageByClient(clientId);
    }

    @GetMapping("{clientId}/stats")
    public ClientStatsResponseDto findClientStats(@PathVariable Long clientId) {
        return clientService.getStats(clientId);
    }

    @GetMapping("{clientId}/abuse")
    public List<AbuseAlertResponseDto> findClientAbuse(@PathVariable Long clientId) {
        return abuseDetectionService.findClientAbuse(clientId);
    }

    @PostMapping("createPlans")
    public PlanDto createPlan(@RequestBody PlanDto planDto) {
        Plan plan = new Plan();
        plan.setName(planDto.planName());
        plan.setPrice(planDto.price());
        plan.setRequestsPerMinute(planDto.requestsPerMinute());
        planRepository.save(plan);

        return planDto;
    }

    @PostMapping("createRouteLimit")
    public RouteLimitDto createRouteLimit(@RequestBody RouteLimitDto routeLimitDto) {
        RouteLimit routeLimit = new RouteLimit();
        routeLimit.setRoutePattern(routeLimitDto.routePattern());
        routeLimit.setRequestsPerMinute(routeLimitDto.requestsPerMinute());

        Plan plan = planRepository.findPlanByName(routeLimitDto.planName()).orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
        routeLimitRepository.save(routeLimit);

        return routeLimitDto;
    }

}
