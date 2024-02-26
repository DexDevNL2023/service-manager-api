package net.service.manager.auth.crud.services.impl;

import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.crud.mapper.DroitMapper;
import net.service.manager.auth.crud.repositories.DroitRepository;
import net.service.manager.auth.crud.repositories.DroitSearchRepository;
import net.service.manager.auth.crud.services.DroitService;
import net.service.manager.auth.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DroitServiceImpl extends ServiceGenericImpl<DroitRequest, DroitReponse, Droit> implements DroitService {
    public DroitServiceImpl(JpaEntityInformation<Droit, Long> entityInformation, DroitRepository repository, DroitSearchRepository searchRepository, DroitMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
