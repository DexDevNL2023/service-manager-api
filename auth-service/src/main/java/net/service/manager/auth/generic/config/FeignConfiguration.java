package net.service.manager.auth.generic.config;

import feign.Logger;
import io.github.resilience4j.retry.Retry;
import net.service.manager.auth.generic.utils.AppConstants;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = {AppConstants.DefaultPackageName})
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {
    @Bean
    public Retry retry() {
        return Retry.ofDefaults(AppConstants.APPLICATION_NAME);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
