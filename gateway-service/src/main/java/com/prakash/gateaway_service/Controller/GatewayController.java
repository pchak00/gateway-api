package com.prakash.gateaway_service.Controller;

import com.prakash.gateaway_service.DTO.*;
import com.prakash.gateaway_service.Entity.AbuseAlert;
import com.prakash.gateaway_service.Service.AbuseDetectionService;
import com.prakash.gateaway_service.Service.ClientService;
import com.prakash.gateaway_service.Service.UsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clients")
public class GatewayController {

    private UsageLogService usageLogService;
    private ClientService clientService;
    private AbuseDetectionService abuseDetectionService;

    GatewayController(UsageLogService usageLogService, ClientService clientService, AbuseDetectionService abuseDetectionService) {
        this.usageLogService = usageLogService;
        this.clientService = clientService;
        this.abuseDetectionService = abuseDetectionService;
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

}
