package net.service.manager.career.crud.repositories;

import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.generic.repository.dao.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends GenericRepository<CareerRequest, Career> {
}
