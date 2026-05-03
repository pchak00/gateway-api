
package com.prakash.gateaway_service.Filter;

import com.prakash.gateaway_service.Entity.Client;
import com.prakash.gateaway_service.Repository.ClientRepository;
import com.prakash.gateaway_service.Service.RateLimiterService;
import org.apache.catalina.util.RateLimiter;
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
    private final RateLimiterService rateLimiterService;

    public ApiKeyFilter(ClientRepository clientRepository, RateLimiterService rateLimiterService) {
        this.clientRepository = clientRepository;
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public ServerResponse filter(ServerRequest request, @NonNull HandlerFunction<ServerResponse> next) throws Exception {

        String apiKey = request.headers().firstHeader("X-API-Key");

        //  Check if header exists
        if (apiKey == null || apiKey.isEmpty()) {
            return ServerResponse.status(401).body("Missing API Key");
        }

        //  Find client
        Optional<Client> clientOpt = clientRepository.findByApiKey(apiKey);

        if (clientOpt.isEmpty()) {
            return ServerResponse.status(401).body("Invalid API Key");
        }

        //Check active
        Client client = clientOpt.get();

        if (!client.getActive()) {
            return ServerResponse.status(403).body("Client is inactive");
        }

        //check rate limit
        boolean isAllowed = rateLimiterService.isAllowed(client.getApiKey(), client.getRequestsPerMinute());
        if (!isAllowed) {
            return ServerResponse.status(429).body("Rate limit exceeded");
        }

        // Continue request
        return next.handle(request);
    }
}