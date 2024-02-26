package net.service.manager.gateway.utils;

import org.springframework.stereotype.Component;

@Component
public class AppConstants {
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer";
    public static final String HEADER_PREFIX = "Bearer ";
}
