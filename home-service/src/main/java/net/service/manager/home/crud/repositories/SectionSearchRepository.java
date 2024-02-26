package net.service.manager.home.crud.repositories;

import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.generic.repository.search.GenericSearchRepository;

public interface SectionSearchRepository extends GenericSearchRepository<SectionRequest, Section> {
}
