package net.service.manager.contact.generic.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.github.resilience4j.retry.Retry;
import net.service.manager.contact.generic.utils.AppConstants;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = {AppConstants.DefaultPackageName})
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

    private final MyJWTConfig myJWTConfig;

    public FeignConfiguration(MyJWTConfig myJWTConfig) {
        this.myJWTConfig = myJWTConfig;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new CustomFeignClientInterceptor(myJWTConfig.getSecret());
    }

    @Bean
    public Retry retry() {
        return Retry.ofDefaults(AppConstants.APPLICATION_NAME);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private static class CustomFeignClientInterceptor implements RequestInterceptor {

        private final String jwtToken;

        public CustomFeignClientInterceptor(String jwtToken) {
            this.jwtToken = jwtToken;
        }

        @Override
        public void apply(RequestTemplate template) {
            // Incluez le JWT dans l'en-tête de toutes les demandes des clients Feign
            template.header(AppConstants.AUTHORIZATION_HEADER, String.format("%s %s", AppConstants.BEARER, jwtToken));
        }
    }
}
