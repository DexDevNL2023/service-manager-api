package net.service.manager.contact.generic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "management.endpoints.web.cors")
public class MyEndpointsManagementConfig {
    private String allowedMapping;
    private String[] allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;
    private Boolean allowedCredentials;
    private String exposedHeaders;
    private Long maxAge;
}
