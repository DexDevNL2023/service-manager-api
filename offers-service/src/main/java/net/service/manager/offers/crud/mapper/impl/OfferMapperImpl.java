package net.service.manager.offers.crud.mapper.impl;

import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.crud.mapper.OfferMapper;
import net.service.manager.offers.crud.repositories.OfferRepository;
import net.service.manager.offers.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class OfferMapperImpl extends GenericMapperImpl<OfferRequest, OfferReponse, Offer> implements OfferMapper {

    protected OfferMapperImpl(Class<Offer> entityClass, Class<OfferReponse> dtoClass, OfferRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Offer newInstance() {
        return new Offer();
    }
}
