package net.service.manager.auth.crud.mapper.impl;

import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.crud.mapper.ModuleMapper;
import net.service.manager.auth.crud.repositories.ModuleRepository;
import net.service.manager.auth.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class ModuleMapperImpl extends GenericMapperImpl<ModuleRequest, ModuleReponse, Module> implements ModuleMapper {

    protected ModuleMapperImpl(Class<Module> entityClass, Class<ModuleReponse> dtoClass, ModuleRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Module newInstance() {
        return new Module();
    }
}
