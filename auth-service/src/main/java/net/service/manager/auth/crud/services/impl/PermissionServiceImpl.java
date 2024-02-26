package net.service.manager.auth.crud.services.impl;

import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.crud.mapper.PermissionMapper;
import net.service.manager.auth.crud.repositories.PermissionRepository;
import net.service.manager.auth.crud.repositories.PermissionSearchRepository;
import net.service.manager.auth.crud.services.PermissionService;
import net.service.manager.auth.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServiceImpl extends ServiceGenericImpl<PermissionRequest, PermissionReponse, Permission> implements PermissionService {
    public PermissionServiceImpl(JpaEntityInformation<Permission, Long> entityInformation, PermissionRepository repository, PermissionSearchRepository searchRepository, PermissionMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
