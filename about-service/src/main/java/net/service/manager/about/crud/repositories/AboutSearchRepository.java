package net.service.manager.about.crud.repositories;

import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.generic.repository.search.GenericSearchRepository;

public interface AboutSearchRepository extends GenericSearchRepository<AboutRequest, About> {
}
