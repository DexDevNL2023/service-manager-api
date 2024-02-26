package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.generic.repository.dao.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<RoleRequest, Role> {
  @Query("SELECT DISTINCT r FROM Role r WHERE r.libelle = :roleName")
  Role findByRoleName(RoleName roleName);
}
