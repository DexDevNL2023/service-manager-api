package net.service.manager.home.generic.client;

import jakarta.validation.constraints.NotNull;
import net.service.manager.home.generic.controller.ControllerGeneric;
import net.service.manager.home.generic.dto.reponse.BaseReponse;
import net.service.manager.home.generic.dto.reponse.RessourceResponse;
import net.service.manager.home.generic.dto.request.BaseRequest;
import net.service.manager.home.generic.dto.request.SearchRequest;
import net.service.manager.home.generic.entity.audit.BaseEntity;
import net.service.manager.home.rest.client.*;
import net.service.manager.home.rest.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class GenericFallback<D extends BaseRequest, R extends BaseReponse, E extends BaseEntity<E, D>> implements ControllerGeneric<D, R, E>,
        AuthorizationService, AboutService, CareerService, ContactService, OfferService, PartnerService {

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

    @Override
    public Optional<String> getCurrentUserLogin() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return Optional.empty();
    }

    @Override
    public Optional<String> getCurrentUserJWT() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return Optional.empty();
    }

    @Override
    public Boolean hasCurrentUserThisAuthority(String authority) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return false;
    }

    @Override
    public Boolean hasCurrentUserAnyOfAuthorities(@NotNull String... authorities) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return false;
    }

    @Override
    public Boolean hasCurrentUserNoneOfAuthorities(@NotNull String... authorities) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return true;
    }

    @Override
    public Boolean isAuthenticated() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return false;
    }

    @Override
    public void checkIfHasDroit(DroitAddRequest dto) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
    }

    @Override
    public AboutReponse findAbout(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PagedModel<AboutReponse> findAllAbout() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public FullCareerReponse findCareer(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PagedModel<FullCareerReponse> findAllCareer() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public ContactReponse findContact(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PagedModel<ContactReponse> findAllContact() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public FullOfferReponse findOffer(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PagedModel<FullOfferReponse> findAllOffers() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PartnerReponse findPartner(Long id) {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }

    @Override
    public PagedModel<PartnerReponse> findAllPartner() {
        logger.error("La méthode distante" + cause.getClass().getName() + " a échoué à cause de : {}", cause.getMessage());
        return null;
    }
}