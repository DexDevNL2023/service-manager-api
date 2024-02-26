package net.service.manager.career.crud.repositories;

import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.generic.repository.search.GenericSearchRepository;

public interface CareerSearchRepository extends GenericSearchRepository<CareerRequest, Career> {
}
