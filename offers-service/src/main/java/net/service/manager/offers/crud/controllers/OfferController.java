package net.service.manager.offers.crud.controllers;

import jakarta.validation.constraints.NotNull;
import net.service.manager.offers.crud.dto.reponse.FullOfferReponse;
import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.generic.controller.ControllerGeneric;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OfferController extends ControllerGeneric<OfferRequest, OfferReponse, Offer> {

    // Rest Client Controllers
    FullOfferReponse find(@NotNull @PathVariable("id") Long id);

    List<FullOfferReponse> findAll();
}
