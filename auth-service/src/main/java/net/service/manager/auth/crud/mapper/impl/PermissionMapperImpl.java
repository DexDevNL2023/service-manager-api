package net.service.manager.auth.crud.mapper.impl;

import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.crud.mapper.PermissionMapper;
import net.service.manager.auth.crud.repositories.PermissionRepository;
import net.service.manager.auth.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class PermissionMapperImpl extends GenericMapperImpl<PermissionRequest, PermissionReponse, Permission> implements PermissionMapper {
    protected PermissionMapperImpl(Class<Permission> entityClass, Class<PermissionReponse> dtoClass, PermissionRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Permission newInstance() {
        return new Permission();
    }
}
