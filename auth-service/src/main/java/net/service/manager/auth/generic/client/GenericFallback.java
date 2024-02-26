package net.service.manager.auth.generic.client;

import net.service.manager.auth.generic.controller.ControllerGeneric;
import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import net.service.manager.auth.generic.dto.reponse.RessourceResponse;
import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.dto.request.SearchRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GenericFallback<D extends BaseRequest, R extends BaseReponse, E extends BaseEntity<E, D>> implements ControllerGeneric<D, R, E> {

    private static final Logger logger = LoggerFactory.getLogger(GenericFallback.class);
    private final Throwable cause;

    public GenericFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseEntity<RessourceResponse> search(SearchRequest dto) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> save(D dto) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> saveAll(List<D> dtos) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> deleteById(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> deleteAll(List<Long> ids) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> getOne(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> getAll() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> getAllByIds(List<Long> ids) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> getByPage(Integer page, Integer size) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<RessourceResponse> update(D dto, Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return new ResponseEntity<>(new RessourceResponse(false, "La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}" + cause.getMessage() + ". Veuillew essayer plutard!!!"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}