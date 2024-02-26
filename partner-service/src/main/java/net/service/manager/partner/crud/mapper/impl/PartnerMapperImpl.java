package net.service.manager.partner.crud.mapper.impl;

import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.crud.mapper.PartnerMapper;
import net.service.manager.partner.crud.repositories.PartnerRepository;
import net.service.manager.partner.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class PartnerMapperImpl extends GenericMapperImpl<PartnerRequest, PartnerReponse, Partner> implements PartnerMapper {

    protected PartnerMapperImpl(Class<Partner> entityClass, Class<PartnerReponse> dtoClass, PartnerRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Partner newInstance() {
        return new Partner();
    }
}
