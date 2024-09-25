package com.clarke.server;

import com.clarke.server.filters.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        
        RouteLocatorBuilder.Builder route = builder.routes()
                .route(p -> p
                        .path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p
                        .path("/currency-conversion/**")
                        .uri("lb://currency-conversion")) 
                .route(p -> p
                        .path("/currency-conversion-new/**")
                .filters(f -> f
                        .rewritePath(
                                "/currency-conversion-new", 
                                "/currency-conversion-feign"))                        
                        .uri("lb://currency-conversion"))                 
                .route(p -> p
                        .path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion")); 
           
        return route.build();
    }
    
//    @Bean
//    public GlobalFilter customFilter() {
//        System.out.println(" C$ GlobalFilter customFilter() ");
//        return new CustomGlobalFilter();
//    }
}
