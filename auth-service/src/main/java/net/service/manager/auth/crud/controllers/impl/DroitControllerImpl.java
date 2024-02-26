package net.service.manager.auth.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.auth.crud.controllers.DroitController;
import net.service.manager.auth.crud.dto.reponse.DroitReponse;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.crud.entities.Droit;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.crud.services.DroitService;
import net.service.manager.auth.generic.controller.impl.ControllerGenericImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/autorisation/droits")
@Tag(name = "Droits", description = "API de gestion des droits")
public class DroitControllerImpl extends ControllerGenericImpl<DroitRequest, DroitReponse, Droit> implements DroitController {
    protected DroitControllerImpl(DroitService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Droit newInstance() {
        return new Droit();
    }
}
