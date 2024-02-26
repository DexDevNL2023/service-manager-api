package net.service.manager.home.crud.services.impl;

import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.crud.mapper.SectionMapper;
import net.service.manager.home.crud.repositories.SectionRepository;
import net.service.manager.home.crud.repositories.SectionSearchRepository;
import net.service.manager.home.crud.services.SectionService;
import net.service.manager.home.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SectionServiceImpl extends ServiceGenericImpl<SectionRequest, SectionReponse, Section> implements SectionService {

    public SectionServiceImpl(JpaEntityInformation<Section, Long> entityInformation, SectionRepository repository, SectionSearchRepository searchRepository, SectionMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
