package net.service.manager.home.crud.repositories;

import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.generic.repository.search.GenericSearchRepository;

public interface HomeSearchRepository extends GenericSearchRepository<HomeRequest, Home> {
}
