package net.service.manager.partner.rest.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import net.service.manager.partner.generic.client.GenericFallbackFactory;
import net.service.manager.partner.generic.config.FeignConfiguration;
import net.service.manager.partner.rest.model.DroitAddRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "auth-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface AuthorizationService {
    @GetMapping("/api/autorisations/current/user/name")
    Optional<String> getCurrentUserLogin();

    @GetMapping("/api/autorisations/current/user/jwt")
    Optional<String> getCurrentUserJWT();

    @GetMapping("/api/autorisations/current/user/have/this/authoritie/{authority}")
    Boolean hasCurrentUserThisAuthority(@NotNull @PathVariable("authority") String authority);

    @GetMapping("/api/autorisations/current/user/any/Of/authorities/{authorities}")
    Boolean hasCurrentUserAnyOfAuthorities(@NotNull @PathVariable("authorities") String... authorities);

    @GetMapping("/api/autorisations/current/user/none/Of/authorities/{authorities}")
    Boolean hasCurrentUserNoneOfAuthorities(@NotNull @PathVariable("authorities") String... authorities);

    @GetMapping("/api/autorisations/current/user/is/authenticated")
    Boolean isAuthenticated();

    @PutMapping("/api/autorisations/add")
    void checkIfHasDroit(@NotEmpty @Valid @RequestBody DroitAddRequest dto);
}
