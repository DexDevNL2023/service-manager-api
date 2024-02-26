package net.service.manager.auth.generic.repository.dao;

import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<D extends BaseRequest, E extends BaseEntity<E, D>> extends JpaRepository<E, Long> {
    String newNumEnrg(String prefixe);

    boolean existsByFieldValue(Object value, String fieldName);

    Page<E> findAllByIsDeleted(boolean isDeleted, Pageable pageable);
}
