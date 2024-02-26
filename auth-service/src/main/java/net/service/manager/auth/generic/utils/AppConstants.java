package net.service.manager.auth.generic.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AppConstants {

    public static final String SYSTEM = "system";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    @Value("${spring.application.name}")
    public static String APPLICATION_NAME;
    @Value("${server.address}")
    public static String SERVEUR_ADRESS;
    @Value("${server.port}")
    public static int SERVEUR_PORT;

    public static final String DefaultPackageName = "net.service.manager.auth";

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "30";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer";
    public static final String HEADER_PREFIX = "Bearer ";
    public static final String TOKEN_INVALID = "INVALID";
    public static final String TOKEN_EXPIRED = "EXPIRED";
    public static final String TOKEN_VALID = "VALID";
    public static final int TOKEN_EXPIRATION = 60 * 24;

    public static final String SUPPORT_EMAIL = "vnlangessama@gmail.com";
    public static final String COMPANY_NAME = "Service Manager app";

    public static final List<String> SEARCHABLE_FIELDS = Arrays.asList("id", "createdAt");
    public static final String PERIODE_FILTABLE_FIELD = "createdAt";
    public static final String NUM_ENRG_FIELD = "numEnrg";

    public static final int THREAD_NUMBER = 4;

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
    public static final int cookieExpireSeconds = 180;
}
