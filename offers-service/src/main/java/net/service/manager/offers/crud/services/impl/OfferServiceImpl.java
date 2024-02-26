package net.service.manager.offers.crud.services.impl;

import net.service.manager.offers.crud.dto.reponse.OfferReponse;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.crud.entities.Offer;
import net.service.manager.offers.crud.mapper.OfferMapper;
import net.service.manager.offers.crud.repositories.OfferRepository;
import net.service.manager.offers.crud.repositories.OfferSearchRepository;
import net.service.manager.offers.crud.services.OfferService;
import net.service.manager.offers.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferServiceImpl extends ServiceGenericImpl<OfferRequest, OfferReponse, Offer> implements OfferService {
    private final OfferRepository repository;

    public OfferServiceImpl(JpaEntityInformation<Offer, Long> entityInformation, OfferRepository repository, OfferSearchRepository searchRepository, OfferMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
        this.repository = repository;
    }

    @Override
    public Offer findOne(Long id) {
        return repository.findById(id).filter(e -> !e.isDeleted()).orElse(null);
    }

    @Override
    public List<Offer> findAll() {
        return repository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }
}
