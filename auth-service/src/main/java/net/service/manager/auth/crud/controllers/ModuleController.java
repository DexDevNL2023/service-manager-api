package net.service.manager.auth.crud.controllers;

import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.generic.controller.ControllerGeneric;

public interface ModuleController extends ControllerGeneric<ModuleRequest, ModuleReponse, Module> {
}
