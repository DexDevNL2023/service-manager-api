package net.service.manager.auth.generic.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import net.service.manager.auth.generic.dto.reponse.PagedResponse;
import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;
import net.service.manager.auth.generic.exceptions.InternalException;
import net.service.manager.auth.generic.exceptions.RessourceNotFoundException;
import net.service.manager.auth.generic.exceptions.SuppressionException;
import net.service.manager.auth.generic.logging.LogExecution;
import net.service.manager.auth.generic.mapper.GenericMapper;
import net.service.manager.auth.generic.repository.dao.GenericRepository;
import net.service.manager.auth.generic.repository.search.GenericSearchRepository;
import net.service.manager.auth.generic.service.ServiceGeneric;
import net.service.manager.auth.generic.utils.AppConstants;
import net.service.manager.auth.generic.utils.GenericUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
public abstract class ServiceGenericImpl<D extends BaseRequest, R extends BaseReponse, E extends BaseEntity<E, D>> implements ServiceGeneric<D, R, E> {

    protected final JpaEntityInformation<E, Long> entityInformation;
    protected final GenericRepository<D, E> repository;
    protected final GenericSearchRepository<D, E> searchRepository;
    protected final GenericMapper<D, R, E> mapper;

    public ServiceGenericImpl(JpaEntityInformation<E, Long> entityInformation, GenericRepository<D, E> repository, GenericSearchRepository<D, E> searchRepository, GenericMapper<D, R, E> mapper) {
        this.entityInformation = entityInformation;
        this.repository = repository;
        this.searchRepository = searchRepository;
        this.mapper = mapper;
    }

    /**
     * @param text
     * @return List<R>
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public List<R> search(String text) throws RessourceNotFoundException {
        return searchRepository.search(text).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param dto
     * @return R
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public R save(D dto) throws RessourceNotFoundException {
        try {
            E e = mapper.toEntity(dto);
            e.setNumEnrg(repository.newNumEnrg(e.getEntityName()));
            e = repository.save(e);
            searchRepository.index(e);
            return getOne(e.getId());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param dtos
     * @return List<R>
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public List<R> saveAll(List<D> dtos) throws RessourceNotFoundException {
        try {
            dtos.forEach(this::save);
            return getAll();
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param dto
     * @param id
     * @return R
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public R update(D dto, Long id) throws RessourceNotFoundException {
        try {
            E entity = getById(id);
            if (entity.equalsToDto(dto))
                throw new RessourceNotFoundException("La ressource " + this.entityInformation.getEntityName() + " avec les données suivante : " + dto.toString() + " existe déjà");
            entity.update(mapper.toEntity(dto));
            entity = repository.save(entity);
            return getOne(entity.getId());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param id
     * @return Boolean
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public Boolean exist(Long id) throws RessourceNotFoundException {
        try {
            return repository.existsById(id);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @return List<R>
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public List<R> getAll() throws RessourceNotFoundException {
        try {
            return repository.findAll().stream().filter(e -> !e.isDeleted()).map(mapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @LogExecution
    public List<R> getAllByIds(List<Long> ids) throws RessourceNotFoundException {
        try {
            return repository.findAllById(ids).stream().filter(e -> !e.isDeleted()).map(mapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param page
     * @param size
     * @return PagedResponse<R>
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public PagedResponse<R> getAllByPage(Integer page, Integer size) throws RessourceNotFoundException {
        try {
            // Vérifier la syntaxe de page et size
            GenericUtils.validatePageNumberAndSize(page, size);
            // Construire la pagination
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, AppConstants.PERIODE_FILTABLE_FIELD);
            // on récupere les données
            Page<E> list = repository.findAllByIsDeleted(false, pageable);
            if (list.getNumberOfElements() == 0)
                throw new RessourceNotFoundException("La recherche de " + this.entityInformation.getEntityName() + " est vide!");
            // Mapper Dto
            List<R> listDto = mapper.toDto(list.getContent());
            return new PagedResponse<R>(listDto, list.getNumber(), list.getSize(), list.getTotalElements(), list.getTotalPages(), list.isLast());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param id
     * @return E
     * @throws RessourceNotFoundException
     */
    @Transactional
    @LogExecution
    private E getById(Long id) throws RessourceNotFoundException {
        try {
            return repository.findById(id).filter(e -> !e.isDeleted()).orElseThrow(
                    () -> new RessourceNotFoundException("La ressource " + this.entityInformation.getEntityName() + " avec l'id " + id + " n'existe pas")
            );
        } catch (Exception e) {
            throw new InternalException("Une erreur est survenue pendant le traitement de votre requête. Cause : " + e.getMessage());
        }
    }

    /**
     * @param id
     * @return R
     * @throws RessourceNotFoundException
     */
    @Override
    @Transactional
    @LogExecution
    public R getOne(Long id) throws RessourceNotFoundException {
        try {
            return mapper.toDto(getById(id));
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param id
     * @throws SuppressionException
     */
    @Override
    @Transactional
    @LogExecution
    public void delete(Boolean isAdmin, Long id) throws SuppressionException {
        try {
            if (!exist(id))
                throw new SuppressionException("La ressource " + this.entityInformation.getEntityName() + " avec l'id " + id + " n'existe pas");
            E entity = getById(id);
            searchRepository.deleteFromIndex(entity);
            if (isAdmin) {
                repository.deleteById(entity.getId());
            } else {
                entity.setIsDeleted(true);
                repository.save(entity);
            }
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    /**
     * @param ids
     * @throws SuppressionException
     */
    @Override
    @Transactional
    @LogExecution
    public void deleteAll(Boolean isAdmin, List<Long> ids) throws SuppressionException {
        try {
            ids.forEach(id -> delete(isAdmin, id));
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @LogExecution
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        try {
            if (value == null) {
                return false;
            }
            return this.repository.existsByFieldValue(value, fieldName);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
