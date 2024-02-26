package net.service.manager.home.crud.mapper.impl;

import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.crud.mapper.HomeMapper;
import net.service.manager.home.crud.repositories.HomeRepository;
import net.service.manager.home.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class HomeMapperImpl extends GenericMapperImpl<HomeRequest, HomeReponse, Home> implements HomeMapper {

    protected HomeMapperImpl(Class<Home> entityClass, Class<HomeReponse> dtoClass, HomeRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Home newInstance() {
        return new Home();
    }
}
