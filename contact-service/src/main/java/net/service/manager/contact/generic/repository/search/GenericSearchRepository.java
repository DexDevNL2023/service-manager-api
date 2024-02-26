package net.service.manager.contact.generic.repository.search;

import net.service.manager.contact.generic.dto.request.BaseRequest;
import net.service.manager.contact.generic.entity.audit.BaseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Référentiel Spring Data Elasticsearch pour l’entité generic.
 */
public interface GenericSearchRepository<D extends BaseRequest, E extends BaseEntity<E, D>> extends ElasticsearchRepository<E, Long>, GenericSearchRepositoryInternal<D, E> {
}
