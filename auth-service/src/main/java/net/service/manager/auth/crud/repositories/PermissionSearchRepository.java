package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;

public interface PermissionSearchRepository extends GenericSearchRepository<PermissionRequest, Permission> {
}
