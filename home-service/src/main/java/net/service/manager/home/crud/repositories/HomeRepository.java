package net.service.manager.home.crud.repositories;

import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.generic.repository.dao.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends GenericRepository<HomeRequest, Home> {
    @Query("SELECT DISTINCT e FROM Home e WHERE e.name = :name")
    Optional<Home> findByName(String name);

    @Query("SELECT e FROM Home e ORDER BY e.createdAt DESC")
    Home findCurrentPage();
}
