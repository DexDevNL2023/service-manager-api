package net.service.manager.auth.generic.config;

import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.services.UserAccountService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final UserAccountService accountService;

    public SetupDataLoader(UserAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        alreadySetup = true;

        accountService.addDefaultUsers(RoleName.ADMIN);
    }
}