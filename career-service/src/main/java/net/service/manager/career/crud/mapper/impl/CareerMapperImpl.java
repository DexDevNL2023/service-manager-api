package net.service.manager.career.crud.mapper.impl;

import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.crud.mapper.CareerMapper;
import net.service.manager.career.crud.repositories.CareerRepository;
import net.service.manager.career.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class CareerMapperImpl extends GenericMapperImpl<CareerRequest, CareerReponse, Career> implements CareerMapper {

    protected CareerMapperImpl(Class<Career> entityClass, Class<CareerReponse> dtoClass, CareerRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Career newInstance() {
        return new Career();
    }
}
