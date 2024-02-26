package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.generic.repository.dao.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends GenericRepository<PermissionRequest, Permission> {
    @Query("SELECT DISTINCT e FROM Permission e WHERE e.role = :role AND e.droit = :droit")
    Permission findByRoleAndDroit(Role role, Droit droit);

    @Query("SELECT DISTINCT e FROM Permission e WHERE e.droit = :droit")
    List<Permission> findAllByDroit(Droit droit);

    @Query("SELECT DISTINCT e FROM Permission e WHERE e.role = :role")
    List<Permission> findAllByRole(Role role);
}
