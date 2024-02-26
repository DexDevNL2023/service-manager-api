package net.service.manager.about.generic.config;

import net.service.manager.about.generic.utils.AppConstants;
import net.service.manager.about.rest.client.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(authorizationService.getCurrentUserLogin().orElse(AppConstants.SYSTEM));
    }
}