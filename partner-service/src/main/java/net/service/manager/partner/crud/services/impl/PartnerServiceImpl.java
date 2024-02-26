package net.service.manager.partner.crud.services.impl;

import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.crud.mapper.PartnerMapper;
import net.service.manager.partner.crud.repositories.PartnerRepository;
import net.service.manager.partner.crud.repositories.PartnerSearchRepository;
import net.service.manager.partner.crud.services.PartnerService;
import net.service.manager.partner.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartnerServiceImpl extends ServiceGenericImpl<PartnerRequest, PartnerReponse, Partner> implements PartnerService {
    public PartnerServiceImpl(JpaEntityInformation<Partner, Long> entityInformation, PartnerRepository repository, PartnerSearchRepository searchRepository, PartnerMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
