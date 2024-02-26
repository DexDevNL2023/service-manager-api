package net.service.manager.offers.crud.repositories;

import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.generic.repository.search.GenericSearchRepository;

public interface OfferSearchRepository extends GenericSearchRepository<OfferRequest, Offer> {
}
