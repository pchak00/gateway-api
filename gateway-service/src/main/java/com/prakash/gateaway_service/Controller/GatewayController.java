package com.prakash.gateaway_service.Controller;

import com.prakash.gateaway_service.DTO.ClientRequestDto;
import com.prakash.gateaway_service.DTO.ClientResponseDto;
import com.prakash.gateaway_service.DTO.ClientStatsResponseDto;
import com.prakash.gateaway_service.DTO.UsageLogResponseDto;
import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Entity.Plan;
import com.prakash.gateaway_service.Repository.ClientRepository;
import com.prakash.gateaway_service.Repository.PlanRepository;
import com.prakash.gateaway_service.Service.ClientService;
import com.prakash.gateaway_service.Service.UsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/clients")
public class GatewayController {

    private UsageLogService usageLogService;
    private ClientService clientService;

    GatewayController(UsageLogService usageLogService, ClientService clientService) {
        this.usageLogService = usageLogService;
        this.clientService = clientService;
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

}
