package net.service.manager.offers.crud.controllers.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.offers.crud.controllers.OfferController;
import net.service.manager.offers.crud.dto.reponse.FullOfferReponse;
import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.crud.services.OfferService;
import net.service.manager.offers.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.offers.generic.utils.GenericMapperUtils;
import net.service.manager.offers.rest.client.AuthorizationService;
import net.service.manager.offers.rest.client.PartnerService;
import net.service.manager.offers.rest.model.PartnerReponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/offers")
@Tag(name = "Offer", description = "API de gestion des services offerts")
public class OfferControllerImpl extends ControllerGenericImpl<OfferRequest, OfferReponse, Offer> implements OfferController {
    private final OfferService service;
    private final PartnerService partnerService;

    protected OfferControllerImpl(OfferService service, AuthorizationService authorizationService, PartnerService partnerService) {
        super(service, authorizationService);
        this.service = service;
        this.partnerService = partnerService;
    }

    @Override
    protected Offer newInstance() {
        return new Offer();
    }

    // Rest Client Controllers
    @Override
    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "Authorization")
    public FullOfferReponse find(Long id) {
        Offer entity = service.findOne(id);
        PartnerReponse partner = partnerService.find(entity.getPartnerId());
        entity.setPartenaire(partner);
        return GenericMapperUtils.map(entity, FullOfferReponse.class);
    }

    @Override
    @GetMapping("/find/all")
    @SecurityRequirement(name = "Authorization")
    public List<FullOfferReponse> findAll() {
        List<Offer> entities = service.findAll();
        entities.forEach(entity -> {
            PartnerReponse partner = partnerService.find(entity.getPartnerId());
            entity.setPartenaire(partner);
        });
        return GenericMapperUtils.mapAll(entities, FullOfferReponse.class);
    }
}
