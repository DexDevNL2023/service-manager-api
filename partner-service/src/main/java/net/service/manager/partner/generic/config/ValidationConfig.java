package net.service.manager.partner.generic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidationConfig {

    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
