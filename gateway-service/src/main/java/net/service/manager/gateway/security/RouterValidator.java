package net.service.manager.gateway.security;

import net.service.manager.gateway.config.MyEndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    @Autowired
    private MyEndpointConfig myEndpointConfig;

    public RouterValidator(MyEndpointConfig myEndpointsConfig) {
        this.myEndpointConfig = myEndpointsConfig;
    }

    public Predicate<ServerHttpRequest> isSecured = request -> Arrays.stream(myEndpointConfig.getOpenApiEndpoints())
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}