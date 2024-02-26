package net.service.manager.partner.crud.repositories;

import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.generic.repository.dao.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends GenericRepository<PartnerRequest, Partner> {
}
