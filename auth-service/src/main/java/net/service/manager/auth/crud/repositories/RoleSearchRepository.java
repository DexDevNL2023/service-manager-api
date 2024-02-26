package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;

public interface RoleSearchRepository extends GenericSearchRepository<RoleRequest, Role> {
}
