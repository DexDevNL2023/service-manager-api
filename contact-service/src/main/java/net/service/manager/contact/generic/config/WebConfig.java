package net.service.manager.contact.generic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Locale;

import static java.net.URLDecoder.decode;

@Configuration
public class WebConfig implements WebMvcConfigurer, WebServerFactoryCustomizer<WebServerFactory> {

    protected static final String[] RESOURCE_LOCATIONS = new String[]{
            "classpath:/static/",
            "classpath:/data/",
            "classpath:/i18n/",
    };
    protected static final String[] RESOURCE_PATHS = new String[]{
            "/*.js",
            "/*.css",
            "/*.svg",
            "/*.png",
            "*.ico",
            "/data/**",
            "/i18n/*",
    };
    private final Logger log = LoggerFactory.getLogger(WebConfig.class);
    private final MyEndpointsManagementConfig myEndpointsManagementConfig;
    private final Environment environment;

    public WebConfig(MyEndpointsManagementConfig myEndpointsManagementConfig, Environment environment) {
        this.myEndpointsManagementConfig = myEndpointsManagementConfig;
        this.environment = environment;
    }

    /**
     * Personnalisez le moteur de servlet : les types MIME, la racine du document, le cache.
     */
    @Override
    public void customize(WebServerFactory server) {
        // When running in an IDE or with ./mvnw spring-boot:run, set location of the static web assets.
        setLocationForStaticAssets(server);
    }

    private void setLocationForStaticAssets(WebServerFactory server) {
        if (server instanceof ConfigurableServletWebServerFactory servletWebServer) {
            File root;
            String prefixPath = resolvePathPrefix();
            root = new File(prefixPath + "target/classes/static/");
            if (root.exists() && root.isDirectory()) {
                servletWebServer.setDocumentRoot(root);
            }
        }
    }

    /**
     * Résoudre le préfixe de chemin vers les ressources statiques.
     */
    private String resolvePathPrefix() {
        String fullExecutablePath = decode(this.getClass().getResource("").getPath(), StandardCharsets.UTF_8);
        String rootPath = Paths.get(".").toUri().normalize().getPath();
        String extractedPath = fullExecutablePath.replace(rootPath, "");
        int extractionEndIndex = extractedPath.indexOf("target/");
        if (extractionEndIndex <= 0) {
            return "";
        }
        return extractedPath.substring(0, extractionEndIndex);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedOriginPatterns())) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", config);
            source.registerCorsConfiguration("/management/**", config);
            source.registerCorsConfiguration("/v3/api-docs", config);
            source.registerCorsConfiguration("/swagger-ui/**", config);
        }
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(myEndpointsManagementConfig.getAllowedMapping())
                .allowedOrigins(myEndpointsManagementConfig.getAllowedOrigins())
                .allowedMethods(myEndpointsManagementConfig.getAllowedMethods())
                .allowedHeaders(myEndpointsManagementConfig.getAllowedHeaders())
                .allowCredentials(myEndpointsManagementConfig.getAllowedCredentials())
                .exposedHeaders(myEndpointsManagementConfig.getExposedHeaders())
                .maxAge(myEndpointsManagementConfig.getMaxAge());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration = appendResourceHandler(registry);
        initializeResourceHandler(resourceHandlerRegistration);
    }

    protected ResourceHandlerRegistration appendResourceHandler(ResourceHandlerRegistry registry) {
        return registry.addResourceHandler(RESOURCE_PATHS);
    }

    protected void initializeResourceHandler(ResourceHandlerRegistration resourceHandlerRegistration) {
        resourceHandlerRegistration.addResourceLocations(RESOURCE_LOCATIONS);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Va intercepter toutes les requetes http://localhost:8080/?language=fr
        // permetant à l'utilisateur de modifier la langue d'affichage des informations
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);

        // Gére la convertion des valeurs des énumérateurs en énumérateur
        ApplicationConversionService.configure(registry);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.FRENCH);
        return cookieLocaleResolver;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }
}