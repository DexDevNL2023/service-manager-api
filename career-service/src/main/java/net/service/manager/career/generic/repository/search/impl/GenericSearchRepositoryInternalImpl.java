package net.service.manager.career.generic.repository.search.impl;


import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import net.service.manager.career.generic.dto.request.BaseRequest;
import net.service.manager.career.generic.entity.audit.BaseEntity;
import net.service.manager.career.generic.repository.dao.GenericRepository;
import net.service.manager.career.generic.repository.search.GenericSearchRepositoryInternal;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;
import java.util.stream.Collectors;

public class GenericSearchRepositoryInternalImpl<D extends BaseRequest, E extends BaseEntity<E, D>> implements GenericSearchRepositoryInternal<D, E> {

    protected final GenericRepository<D, E> repository;
    private final Class<E> clazz;
    private final ElasticsearchTemplate elasticsearchTemplate;

    GenericSearchRepositoryInternalImpl(Class<E> clazz, ElasticsearchTemplate elasticsearchTemplate, GenericRepository<D, E> repository) {
        this.clazz = clazz;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public List<E> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return elasticsearchTemplate.search(nativeQuery, clazz).map(SearchHit::getContent).stream().collect(Collectors.toList());
    }

    @Override
    public void index(E entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndex(E entity) {
        elasticsearchTemplate.delete(entity);
    }
}
