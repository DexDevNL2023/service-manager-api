package net.service.manager.about.crud.mapper.impl;

import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.crud.mapper.AboutMapper;
import net.service.manager.about.crud.repositories.AboutRepository;
import net.service.manager.about.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class AboutMapperImpl extends GenericMapperImpl<AboutRequest, AboutReponse, About> implements AboutMapper {

    protected AboutMapperImpl(Class<About> entityClass, Class<AboutReponse> dtoClass, AboutRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected About newInstance() {
        return new About();
    }
}
