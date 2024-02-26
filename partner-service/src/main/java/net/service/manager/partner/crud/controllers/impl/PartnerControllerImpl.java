package net.service.manager.partner.crud.controllers.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.partner.crud.controllers.PartnerController;
import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.crud.services.PartnerService;
import net.service.manager.partner.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.partner.rest.client.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/partners")
@Tag(name = "Partners", description = "API de gestion des partenaires")
public class PartnerControllerImpl extends ControllerGenericImpl<PartnerRequest, PartnerReponse, Partner> implements PartnerController {
    private final PartnerService service;

    protected PartnerControllerImpl(PartnerService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
        this.service = service;
    }

    @Override
    protected Partner newInstance() {
        return new Partner();
    }

    // Rest Client Controllers
    @Override
    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "Authorization")
    public PartnerReponse find(Long id) {
        return service.getOne(id);
    }

    @Override
    @GetMapping("/find/all")
    @SecurityRequirement(name = "Authorization")
    public List<PartnerReponse> findAll() {
        return service.getAll();
    }
}
