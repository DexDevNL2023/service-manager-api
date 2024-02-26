package net.service.manager.auth.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.auth.crud.controllers.ModuleController;
import net.service.manager.auth.crud.dto.reponse.ModuleReponse;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.crud.entities.Module;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.crud.services.ModuleService;
import net.service.manager.auth.generic.controller.impl.ControllerGenericImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/autorisation/modules")
@Tag(name = "Modules", description = "API de gestion des modules applicatifs")
public class ModuleControllerImpl extends ControllerGenericImpl<ModuleRequest, ModuleReponse, Module> implements ModuleController {
    protected ModuleControllerImpl(ModuleService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Module newInstance() {
        return new Module();
    }
}
