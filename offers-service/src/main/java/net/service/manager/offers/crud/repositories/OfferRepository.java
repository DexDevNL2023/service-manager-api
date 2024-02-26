package net.service.manager.offers.crud.repositories;

import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.generic.repository.dao.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends GenericRepository<OfferRequest, Offer> {
}
