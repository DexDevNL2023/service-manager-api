package net.service.manager.auth.crud.controllers;

import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.generic.controller.ControllerGeneric;

public interface PermissionController extends ControllerGeneric<PermissionRequest, PermissionReponse, Permission> {
}
