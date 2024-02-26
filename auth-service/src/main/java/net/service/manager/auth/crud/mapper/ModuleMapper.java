package net.service.manager.auth.crud.mapper;

import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.generic.mapper.GenericMapper;

public interface ModuleMapper extends GenericMapper<ModuleRequest, ModuleReponse, Module> {
}
