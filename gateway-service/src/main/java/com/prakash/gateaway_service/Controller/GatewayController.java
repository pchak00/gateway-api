package com.prakash.gateaway_service.Controller;

import com.prakash.gateaway_service.DTO.ClientRequestDto;
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

    private ClientRepository clientRepository;
    private UsageLogService usageLogService;
    private ClientService clientService;
    private PlanRepository planRepository;

    GatewayController(ClientRepository clientRepository,  UsageLogService usageLogService, ClientService clientService,  PlanRepository planRepository) {
        this.clientRepository = clientRepository;
        this.usageLogService = usageLogService;
        this.clientService = clientService;
        this.planRepository = planRepository;
    }

    @PostMapping
    public Client createClient(@RequestBody ClientRequestDto clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.name());
        Plan plan = planRepository.findPlanByName(clientRequest.planName()).orElseThrow(() -> new RuntimeException("Plan not found"));
        client.setPlan(plan);
        plan.addClient(client);
        client.setActive(clientRequest.active());
        client.setApiKey(UUID.randomUUID().toString()); // api key generator

        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> showAllClient() {
        return clientRepository.findAll();
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
