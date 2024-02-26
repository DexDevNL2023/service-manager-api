package net.service.manager.about.crud.repositories;

import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.generic.repository.dao.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends GenericRepository<AboutRequest, About> {
}
