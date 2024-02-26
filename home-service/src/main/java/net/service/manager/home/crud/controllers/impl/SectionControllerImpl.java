package net.service.manager.home.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.home.crud.controllers.SectionController;
import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.crud.services.SectionService;
import net.service.manager.home.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.home.rest.client.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sections")
@Tag(name = "Sections", description = "API de gestion des sections")
public class SectionControllerImpl extends ControllerGenericImpl<SectionRequest, SectionReponse, Section> implements SectionController {

    protected SectionControllerImpl(SectionService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Section newInstance() {
        return new Section();
    }
}
