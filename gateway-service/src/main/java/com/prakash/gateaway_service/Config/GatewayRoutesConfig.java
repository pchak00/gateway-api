package com.prakash.gateaway_service.Config;

import com.prakash.gateaway_service.Filter.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayRoutesConfig {

    @Value("${backend.service.url}")
    private String backendServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> backendRoute(ApiKeyFilter apiKeyFilter) {
        return route("backend-service-route")
                .GET("/api/**", http())
                .filter(apiKeyFilter)
                .before(uri(backendServiceUrl))
                .build();
    }
}