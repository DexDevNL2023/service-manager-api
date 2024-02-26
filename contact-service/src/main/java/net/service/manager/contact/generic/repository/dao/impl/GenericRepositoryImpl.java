package net.service.manager.contact.generic.repository.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.service.manager.contact.generic.dto.request.BaseRequest;
import net.service.manager.contact.generic.entity.audit.BaseEntity;
import net.service.manager.contact.generic.repository.dao.GenericRepository;
import net.service.manager.contact.generic.utils.AppConstants;
import net.service.manager.contact.generic.utils.GenericUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.lang.reflect.Field;
import java.util.Optional;

@Transactional
public abstract class GenericRepositoryImpl<D extends BaseRequest, E extends BaseEntity<E, D>> extends SimpleJpaRepository<E, Long> implements GenericRepository<D, E> {
    private final Class<E> clazz;
    private final JpaEntityInformation<E, Long> entityInformation;
    @PersistenceContext
    private final EntityManager entityManager;

    public GenericRepositoryImpl(Class<E> clazz, JpaEntityInformation<E, Long> entityInformation, EntityManager entityManager) {
        super(clazz, entityManager);
        this.clazz = clazz;
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    public GenericRepositoryImpl(JpaEntityInformation<E, Long> entityInformation, Class<E> clazz, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.clazz = clazz;
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public String newNumEnrg(String prefixe) {
        String num = "";
        String fieldName = AppConstants.NUM_ENRG_FIELD;
        entityManager.getTransaction().begin();
        try {
            if (!isFieldExist(fieldName)) {
                throw new UnsupportedOperationException("Le champ " + fieldName + " non pris en charge");
            }
            do {
                String newNum = GenericUtils.GenerateNumEnrg(prefixe);
                String queryString = String.format("select from %s x where %s = :newNum",
                        this.entityInformation.getEntityName(),
                        "%s",
                        fieldName);
                final E result = entityManager.createQuery(queryString, clazz).
                        setParameter("newNum", newNum)
                        .getSingleResult();
                if (result == null) {
                    num = newNum;
                }
            } while (!num.isEmpty());
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return num;
    }

    @Override
    public boolean existsByFieldValue(Object value, String fieldName) {
        entityManager.getTransaction().begin();
        try {
            if (!isFieldExist(fieldName)) {
                throw new UnsupportedOperationException("Le champ " + fieldName + " non pris en charge");
            }
            String queryString = String.format("select from %s x where %s = :value",
                    this.entityInformation.getEntityName(),
                    "%s",
                    fieldName);
            final Optional<E> result = Optional.ofNullable(entityManager.createQuery(queryString, clazz).
                    setParameter("value", value.toString())
                    .getSingleResult());
            entityManager.getTransaction().commit();
            return result.isPresent();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        return false;
    }

    public boolean isFieldExist(String fieldName) {
        for (Field field : clazz.getFields()) {
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}