package net.service.manager.career.crud.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.career.crud.enums.CareerType;
import net.service.manager.career.generic.enums.EnumValue;
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
    @GetMapping(value = "/career-type", produces = MediaTypes.HAL_JSON_VALUE)
    @SecurityRequirement(name = "Authorization")
    public List<EnumValue> getCareerTypes() {
        return CareerType.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }
}
