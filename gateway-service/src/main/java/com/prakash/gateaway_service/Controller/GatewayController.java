package com.prakash.gateaway_service.Controller;

import com.prakash.gateaway_service.DTO.ClientRequestDto;
import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/clients")
public class GatewayController {
    private ClientService clientService;
    GatewayController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client createClient(@RequestBody ClientRequestDto clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.name());
        client.setRequestsPerMinute(clientRequest.requestPerMinute());
        client.setActive(clientRequest.active());
        client.setApiKey(UUID.randomUUID().toString()); // api key generator

        return clientService.saveClient(client);
    }

    @GetMapping
    public List<Client> showAllClient() {
        return clientService.showAllCLient();
    }


}
