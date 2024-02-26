package net.service.manager.partner.crud.controllers;

import jakarta.validation.constraints.NotNull;
import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.generic.controller.ControllerGeneric;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PartnerController extends ControllerGeneric<PartnerRequest, PartnerReponse, Partner> {

    // Rest Client Controllers
    PartnerReponse find(@NotNull @PathVariable("id") Long id);

    List<PartnerReponse> findAll();
}
