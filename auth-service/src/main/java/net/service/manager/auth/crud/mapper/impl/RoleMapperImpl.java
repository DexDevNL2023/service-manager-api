package net.service.manager.auth.crud.mapper.impl;

import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.crud.mapper.RoleMapper;
import net.service.manager.auth.crud.repositories.RoleRepository;
import net.service.manager.auth.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class RoleMapperImpl extends GenericMapperImpl<RoleRequest, RoleReponse, Role> implements RoleMapper {
    protected RoleMapperImpl(Class<Role> entityClass, Class<RoleReponse> dtoClass, RoleRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Role newInstance() {
        return new Role();
    }
}
