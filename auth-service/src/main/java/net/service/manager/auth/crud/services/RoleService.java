package net.service.manager.auth.crud.services;

import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.generic.service.ServiceGeneric;

public interface RoleService extends ServiceGeneric<RoleRequest, RoleReponse, Role> {
    void addDefaultRoles();
}
