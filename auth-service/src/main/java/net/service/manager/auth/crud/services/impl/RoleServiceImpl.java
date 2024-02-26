package net.service.manager.auth.crud.services.impl;

import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.mapper.RoleMapper;
import net.service.manager.auth.crud.repositories.RoleRepository;
import net.service.manager.auth.crud.repositories.RoleSearchRepository;
import net.service.manager.auth.crud.services.RoleService;
import net.service.manager.auth.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends ServiceGenericImpl<RoleRequest, RoleReponse, Role> implements RoleService {

    private  final RoleRepository repository;

    public RoleServiceImpl(JpaEntityInformation<Role, Long> entityInformation, RoleRepository repository, RoleSearchRepository searchRepository, RoleMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
        this.repository = repository;
    }

    @Override
    public void addDefaultRoles() {
        for (RoleName roleName : RoleName.valuesInList()) {
            if (repository.findByRoleName(roleName) == null) {
                Role authority = new Role();
                authority.setIsSuper(roleName.equals(RoleName.ADMIN));
                authority.setLibelle(roleName);
                repository.save(authority);
            }
        }
    }
}
