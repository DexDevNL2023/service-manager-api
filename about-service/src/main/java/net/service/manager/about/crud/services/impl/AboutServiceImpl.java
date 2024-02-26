package net.service.manager.about.crud.services.impl;

import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.crud.mapper.AboutMapper;
import net.service.manager.about.crud.repositories.AboutRepository;
import net.service.manager.about.crud.repositories.AboutSearchRepository;
import net.service.manager.about.crud.services.AboutService;
import net.service.manager.about.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AboutServiceImpl extends ServiceGenericImpl<AboutRequest, AboutReponse, About> implements AboutService {
    public AboutServiceImpl(JpaEntityInformation<About, Long> entityInformation, AboutRepository repository, AboutSearchRepository searchRepository, AboutMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
