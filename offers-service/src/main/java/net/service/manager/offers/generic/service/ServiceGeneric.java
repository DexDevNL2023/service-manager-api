package net.service.manager.offers.generic.service;

import net.service.manager.offers.generic.dto.reponse.BaseReponse;
import net.service.manager.offers.generic.dto.reponse.PagedResponse;
import net.service.manager.offers.generic.dto.request.BaseRequest;
import net.service.manager.offers.generic.entity.audit.BaseEntity;
import net.service.manager.offers.generic.exceptions.RessourceNotFoundException;
import net.service.manager.offers.generic.exceptions.SuppressionException;
import net.service.manager.offers.generic.validators.unique.FieldValueExists;

import java.util.List;

public interface ServiceGeneric<D extends BaseRequest, R extends BaseReponse, E extends BaseEntity<E, D>> extends FieldValueExists {
    List<R> search(String text) throws RessourceNotFoundException;

    R save(D dto) throws RessourceNotFoundException;

    List<R> saveAll(List<D> dtos) throws RessourceNotFoundException;

    void delete(Boolean isAdmin, Long id) throws SuppressionException;

    void deleteAll(Boolean isAdmin, List<Long> ids) throws SuppressionException;

    Boolean exist(Long id) throws RessourceNotFoundException;

    R getOne(Long id) throws RessourceNotFoundException;

    List<R> getAll() throws RessourceNotFoundException;

    List<R> getAllByIds(List<Long> ids) throws RessourceNotFoundException;

    PagedResponse<R> getAllByPage(Integer page, Integer size) throws RessourceNotFoundException;

    R update(D dto, Long id) throws RessourceNotFoundException;
}
