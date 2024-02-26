package net.service.manager.partner.crud.services;

import net.service.manager.partner.crud.dto.reponse.PartnerReponse;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.generic.service.ServiceGeneric;

public interface PartnerService extends ServiceGeneric<PartnerRequest, PartnerReponse, Partner> {
}
