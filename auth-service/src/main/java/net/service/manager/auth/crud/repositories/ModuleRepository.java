package net.service.manager.auth.crud.repositories;

import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.generic.repository.dao.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepository extends GenericRepository<ModuleRequest, Module> {
    @Query("SELECT DISTINCT e FROM Module e WHERE e.name = :name")
    Optional<Module> findByName(String name);
}
