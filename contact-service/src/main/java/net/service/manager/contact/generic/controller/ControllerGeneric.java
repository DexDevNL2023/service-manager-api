package net.service.manager.contact.generic.controller;

import net.service.manager.contact.generic.dto.reponse.BaseReponse;
import net.service.manager.contact.generic.dto.reponse.RessourceResponse;
import net.service.manager.contact.generic.dto.request.BaseRequest;
import net.service.manager.contact.generic.dto.request.SearchRequest;
import net.service.manager.contact.generic.entity.audit.BaseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerGeneric<D extends BaseRequest, R extends BaseReponse, E extends BaseEntity<E, D>> {
    ResponseEntity<RessourceResponse> search(SearchRequest dto);

    ResponseEntity<RessourceResponse> save(D dto);

    ResponseEntity<RessourceResponse> saveAll(List<D> dtos);

    ResponseEntity<RessourceResponse> deleteById(Long id);

    ResponseEntity<RessourceResponse> deleteAll(List<Long> ids);

    ResponseEntity<RessourceResponse> getOne(Long id);

    ResponseEntity<RessourceResponse> getAll();

    ResponseEntity<RessourceResponse> getAllByIds(List<Long> ids);

    ResponseEntity<RessourceResponse> getByPage(Integer page, Integer size);

    ResponseEntity<RessourceResponse> update(D dto, Long id);
}
