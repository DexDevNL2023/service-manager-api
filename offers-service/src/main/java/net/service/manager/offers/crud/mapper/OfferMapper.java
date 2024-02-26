package net.service.manager.offers.crud.mapper;

import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.generic.mapper.GenericMapper;

public interface OfferMapper extends GenericMapper<OfferRequest, OfferReponse, Offer> {
}
