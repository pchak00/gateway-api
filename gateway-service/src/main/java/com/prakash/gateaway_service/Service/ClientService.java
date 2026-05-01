package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> findClientbyKey(String apiKey) {
        return clientRepository.findByApiKey(apiKey);
    }
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    public List<Client> showAllCLient() {
        return clientRepository.findAll();
    }

}
