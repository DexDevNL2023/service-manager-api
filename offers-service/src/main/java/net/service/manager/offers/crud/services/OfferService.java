package net.service.manager.offers.crud.services;

import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.generic.service.ServiceGeneric;

import java.util.List;

public interface OfferService extends ServiceGeneric<OfferRequest, OfferReponse, Offer> {
    Offer findOne(Long id);

    List<Offer> findAll();
}
