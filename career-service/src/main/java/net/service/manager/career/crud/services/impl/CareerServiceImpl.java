package net.service.manager.career.crud.services.impl;

import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.crud.mapper.CareerMapper;
import net.service.manager.career.crud.repositories.CareerRepository;
import net.service.manager.career.crud.repositories.CareerSearchRepository;
import net.service.manager.career.crud.services.CareerService;
import net.service.manager.career.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CareerServiceImpl extends ServiceGenericImpl<CareerRequest, CareerReponse, Career> implements CareerService {
    private final CareerRepository repository;

    public CareerServiceImpl(JpaEntityInformation<Career, Long> entityInformation, CareerRepository repository, CareerSearchRepository searchRepository, CareerMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
        this.repository = repository;
    }

    @Override
    public Career findOne(Long id) {
        return repository.findById(id).filter(e -> !e.isDeleted()).orElse(null);
    }

    @Override
    public List<Career> findAll() {
        return repository.findAll().stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }
}
