package net.service.manager.auth.crud.mapper;

import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.generic.mapper.GenericMapper;

public interface RoleMapper extends GenericMapper<RoleRequest, RoleReponse, Role> {
}
