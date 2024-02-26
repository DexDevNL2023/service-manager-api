package net.service.manager.partner.crud.repositories;

import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.generic.repository.search.GenericSearchRepository;

public interface PartnerSearchRepository extends GenericSearchRepository<PartnerRequest, Partner> {
}
