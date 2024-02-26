package net.service.manager.home.crud.repositories;

import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.generic.repository.dao.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends GenericRepository<SectionRequest, Section> {
    @Query("SELECT DISTINCT e FROM Section e WHERE e.key = :key")
    Optional<Section> findByKey(String key);
}
