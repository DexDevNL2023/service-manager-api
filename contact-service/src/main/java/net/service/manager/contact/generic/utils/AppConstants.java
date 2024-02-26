package net.service.manager.contact.generic.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AppConstants {

    public static final String SYSTEM = "system";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String DefaultPackageName = "net.service.manager.contact";

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "30";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    @Value("${server.address}")
    public static String SERVEUR_ADRESS;
    @Value("${server.port}")
    public static int SERVEUR_PORT;
    public static final String BEARER = "Bearer";
    @Value("${spring.application.name}")
    public static String APPLICATION_NAME;

    public static String SUPPORT_EMAIL = "vnlangessama@gmail.com";
    public static String COMPANY_NAME = "Service Manager app";

    public static List<String> SEARCHABLE_FIELDS = Arrays.asList("id", "createdAt");
    public static String PERIODE_FILTABLE_FIELD = "createdAt";
    public static String NUM_ENRG_FIELD = "numEnrg";

    public static int THREAD_NUMBER = 4;

    public static String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static int cookieExpireSeconds = 180;
}
