package net.service.manager.auth.crud.services;

import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.generic.service.ServiceGeneric;

public interface DroitService extends ServiceGeneric<DroitRequest, DroitReponse, Droit> {
}
