
package com.prakash.gateaway_service.Filter;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Repository.ClientRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Optional;

@Component
public class ApiKeyFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private final ClientRepository clientRepository;

    public ApiKeyFilter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ServerResponse filter(ServerRequest request, @NonNull HandlerFunction<ServerResponse> next) throws Exception {

        String apiKey = request.headers().firstHeader("X-API-Key");

        // 1. Check if header exists
        if (apiKey == null || apiKey.isEmpty()) {
            return ServerResponse.status(401).body("Missing API Key");
        }

        // 2. Find client
        Optional<Client> clientOpt = clientRepository.findByApiKey(apiKey);

        if (clientOpt.isEmpty()) {
            return ServerResponse.status(401).body("Invalid API Key");
        }

        // 3. Check active
        Client client = clientOpt.get();

        if (!client.getActive()) {
            return ServerResponse.status(403).body("Client is inactive");
        }

        // 4. Continue request
        return next.handle(request);
    }
}