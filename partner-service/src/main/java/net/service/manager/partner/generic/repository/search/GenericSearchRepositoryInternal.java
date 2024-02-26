package net.service.manager.partner.generic.repository.search;

import net.service.manager.partner.generic.dto.request.BaseRequest;
import net.service.manager.partner.generic.entity.audit.BaseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenericSearchRepositoryInternal<D extends BaseRequest, E extends BaseEntity<E, D>> {
    List<E> search(String query);

    @Async
    @Transactional
    void index(E entity);

    @Async
    @Transactional
    void deleteFromIndex(E entity);
}
