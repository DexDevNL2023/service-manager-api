package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;

public interface DroitSearchRepository extends GenericSearchRepository<DroitRequest, Droit> {
}
