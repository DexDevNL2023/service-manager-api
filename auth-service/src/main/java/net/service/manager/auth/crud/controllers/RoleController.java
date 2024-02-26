package net.service.manager.auth.crud.controllers;

import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.generic.controller.ControllerGeneric;

public interface RoleController extends ControllerGeneric<RoleRequest, RoleReponse, Role> {
}
