package net.service.manager.auth.crud.mapper.impl;

import net.service.manager.auth.generic.mapper.impl.GenericMapperImpl;
import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.crud.mapper.DroitMapper;
import net.service.manager.auth.crud.repositories.DroitRepository;
import org.mapstruct.Mapper;

@Mapper
public class DroitMapperImplImpl extends GenericMapperImpl<DroitRequest, DroitReponse, Droit> implements DroitMapper {
    protected DroitMapperImplImpl(Class<Droit> entityClass, Class<DroitReponse> dtoClass, DroitRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Droit newInstance() {
        return new Droit();
    }
}
