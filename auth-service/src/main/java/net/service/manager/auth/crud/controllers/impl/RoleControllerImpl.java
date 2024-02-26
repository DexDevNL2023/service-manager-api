package net.service.manager.auth.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.auth.crud.controllers.RoleController;
import net.service.manager.auth.crud.dto.reponse.RoleReponse;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.entities.Role;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.crud.services.RoleService;
import net.service.manager.auth.generic.controller.impl.ControllerGenericImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/autorisation/roles")
@Tag(name = "Roles", description = "API de gestion des rôles")
public class RoleControllerImpl extends ControllerGenericImpl<RoleRequest, RoleReponse, Role> implements RoleController {
    protected RoleControllerImpl(RoleService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Role newInstance() {
        return new Role();
    }
}
