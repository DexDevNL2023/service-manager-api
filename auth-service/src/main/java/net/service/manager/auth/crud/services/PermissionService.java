package net.service.manager.auth.crud.services;

import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.generic.service.ServiceGeneric;

public interface PermissionService extends ServiceGeneric<PermissionRequest, PermissionReponse, Permission> {
}
