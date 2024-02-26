package net.service.manager.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "management.endpoints.web.cors")
public class MyEndpointConfig {
    private String[] openApiEndpoints;
}
