package net.service.manager.auth.crud.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.enums.SocialProvider;
import net.service.manager.auth.generic.enums.EnumValue;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/enums")
@RestController
@Slf4j
public class EnumController {
    //Ennumerateur
    @GetMapping(value = "/roles", produces = MediaTypes.HAL_JSON_VALUE)
    @SecurityRequirement(name = "Authorization")
    public List<EnumValue> getRoleNames() {
        return RoleName.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/social-provider", produces = MediaTypes.HAL_JSON_VALUE)
    @SecurityRequirement(name = "Authorization")
    public List<EnumValue> getSocialProviders() {
        return SocialProvider.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }
}
