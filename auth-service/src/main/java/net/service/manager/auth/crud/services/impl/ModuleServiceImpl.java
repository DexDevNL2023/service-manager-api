package net.service.manager.auth.crud.services.impl;

import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.crud.mapper.ModuleMapper;
import net.service.manager.auth.crud.repositories.ModuleRepository;
import net.service.manager.auth.crud.repositories.ModuleSearchRepository;
import net.service.manager.auth.crud.services.ModuleService;
import net.service.manager.auth.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModuleServiceImpl extends ServiceGenericImpl<ModuleRequest, ModuleReponse, Module> implements ModuleService {
    public ModuleServiceImpl(JpaEntityInformation<Module, Long> entityInformation, ModuleRepository repository, ModuleSearchRepository searchRepository, ModuleMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
