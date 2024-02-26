package net.service.manager.home.crud.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.reponse.LiteSectionReponse;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.crud.services.HomeService;
import net.service.manager.home.generic.enums.EnumValue;
import net.service.manager.home.rest.enums.CareerType;
import net.service.manager.home.rest.enums.ContactType;
import net.service.manager.home.rest.enums.PartnerType;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/public")
@RestController
@Slf4j
public class PublicController {
    private final HomeService service;

    public PublicController(HomeService service) {
        this.service = service;
    }

    @GetMapping("/build/home-page")
    public HomeReponse buildHomePage() {
        return service.getCurrentHomePage();
    }

    @GetMapping("/details/section/{sectionId}/{type}")
    public LiteSectionReponse detailsSectionPage(@NotNull @PathVariable("sectionId") Long sectionId, @NotNull @PathVariable("type") SectionType type) {
        return service.detailsSectionPage(sectionId, type);
    }

    //Ennumerateur
    @GetMapping(value = "/enums/career-type", produces = MediaTypes.HAL_JSON_VALUE)
    public List<EnumValue> getCareerTypes() {
        return CareerType.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/enums/contact-type", produces = MediaTypes.HAL_JSON_VALUE)
    @SecurityRequirement(name = "Authorization")
    public List<EnumValue> getContactTypes() {
        return ContactType.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/enums/partner-type", produces = MediaTypes.HAL_JSON_VALUE)
    public List<EnumValue> getPartnerTypes() {
        return PartnerType.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/enums/home-section", produces = MediaTypes.HAL_JSON_VALUE)
    public List<EnumValue> getHomeSections() {
        return SectionType.valuesInList().stream()
                .map(e -> new EnumValue(e.name(), e.getValue()))
                .collect(Collectors.toList());
    }
}
