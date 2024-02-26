package net.service.manager.gateway.config;

import net.service.manager.gateway.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter filter;

    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("lb://auth-service"))
                .route("about-service", r -> r.path("/api/about/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://about-service"))
                .route("career-service", r -> r.path("/api/career/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://career-service"))
                .route("contact-service", r -> r.path("/api/contact/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://contact-service"))
                .route("home-service", r -> r.path("/api/home/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://home-service"))
                .route("offers-service", r -> r.path("/api/offers/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://offers-service"))
                .build();
    }
}
