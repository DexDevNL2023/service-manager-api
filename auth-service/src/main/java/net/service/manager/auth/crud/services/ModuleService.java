package net.service.manager.auth.crud.services;

import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.generic.service.ServiceGeneric;

public interface ModuleService extends ServiceGeneric<ModuleRequest, ModuleReponse, Module> {
}
