package net.service.manager.career.crud.controllers.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.career.crud.controllers.CareerController;
import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.reponse.FullCareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.crud.services.CareerService;
import net.service.manager.career.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.career.generic.utils.GenericMapperUtils;
import net.service.manager.career.rest.client.AuthorizationService;
import net.service.manager.career.rest.client.PartnerService;
import net.service.manager.career.rest.model.PartnerReponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/careers")
@Tag(name = "Careers", description = "API de gestion des carrieres")
public class CareerControllerImpl extends ControllerGenericImpl<CareerRequest, CareerReponse, Career> implements CareerController {
    private final CareerService service;
    private final PartnerService partnerService;

    protected CareerControllerImpl(CareerService service, AuthorizationService authorizationService, PartnerService partnerService) {
        super(service, authorizationService);
        this.service = service;
        this.partnerService = partnerService;
    }

    @Override
    protected Career newInstance() {
        return new Career();
    }

    // Rest Client Controllers
    @Override
    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "Authorization")
    public FullCareerReponse find(Long id) {
        Career entity = service.findOne(id);
        PartnerReponse partner = partnerService.find(entity.getPartnerId());
        entity.setPartenaire(partner);
        return GenericMapperUtils.map(entity, FullCareerReponse.class);
    }

    @Override
    @GetMapping("/find/all")
    @SecurityRequirement(name = "Authorization")
    public List<FullCareerReponse> findAll() {
        List<Career> entities = service.findAll();
        entities.forEach(entity -> {
            PartnerReponse partner = partnerService.find(entity.getPartnerId());
            entity.setPartenaire(partner);
        });
        return GenericMapperUtils.mapAll(entities, FullCareerReponse.class);
    }
}
