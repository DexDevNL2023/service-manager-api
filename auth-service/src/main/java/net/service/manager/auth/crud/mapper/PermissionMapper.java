package net.service.manager.auth.crud.mapper;

import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.generic.mapper.GenericMapper;

public interface PermissionMapper extends GenericMapper<PermissionRequest, PermissionReponse, Permission> {
}
