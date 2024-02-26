package net.service.manager.partner.crud.mapper;

import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.generic.mapper.GenericMapper;

public interface PartnerMapper extends GenericMapper<PartnerRequest, PartnerReponse, Partner> {
}
