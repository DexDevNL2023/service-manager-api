package net.service.manager.home.crud.mapper.impl;

import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.crud.mapper.SectionMapper;
import net.service.manager.home.crud.repositories.SectionRepository;
import net.service.manager.home.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class SectionMapperImpl extends GenericMapperImpl<SectionRequest, SectionReponse, Section> implements SectionMapper {

    protected SectionMapperImpl(Class<Section> entityClass, Class<SectionReponse> dtoClass, SectionRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Section newInstance() {
        return new Section();
    }
}
