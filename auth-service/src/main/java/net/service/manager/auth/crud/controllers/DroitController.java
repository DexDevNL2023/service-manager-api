package net.service.manager.auth.crud.controllers;

import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.generic.controller.ControllerGeneric;

public interface DroitController extends ControllerGeneric<DroitRequest, DroitReponse, Droit> {
}
