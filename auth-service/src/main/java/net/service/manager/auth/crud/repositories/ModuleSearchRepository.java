package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;

public interface ModuleSearchRepository extends GenericSearchRepository<ModuleRequest, Module> {
}
