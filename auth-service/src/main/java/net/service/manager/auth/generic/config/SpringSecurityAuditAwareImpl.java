package net.service.manager.auth.generic.config;

import net.service.manager.auth.generic.utils.AppConstants;
import net.service.manager.auth.security.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(AppConstants.SYSTEM));
    }
}