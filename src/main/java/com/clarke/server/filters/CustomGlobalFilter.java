package com.clarke.server.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//import jakarta.persistence.criteria.Order;

@Component
public class CustomGlobalFilter implements GlobalFilter {
    
    private final Logger log = LoggerFactory.getLogger(CustomGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        RequestPath path = exchange.getRequest().getPath();
        System.out.println(" Hello Big Boy!!");
        log.debug("C$ First Filter Logger " + path.contextPath());
        return chain.filter(exchange);
    }

}
