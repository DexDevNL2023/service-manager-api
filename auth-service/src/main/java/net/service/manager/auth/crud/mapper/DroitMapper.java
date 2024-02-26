package net.service.manager.auth.crud.mapper;

import net.service.manager.auth.generic.mapper.GenericMapper;
import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;

public interface DroitMapper extends GenericMapper<DroitRequest, DroitReponse, Droit> {
}
