package net.service.manager.auth.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.auth.crud.controllers.PermissionController;
import net.service.manager.auth.crud.dto.reponse.PermissionReponse;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.crud.entities.Permission;
import net.service.manager.auth.crud.services.AuthorizationService;
import net.service.manager.auth.crud.services.PermissionService;
import net.service.manager.auth.generic.controller.impl.ControllerGenericImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/autorisation/permissions")
@Tag(name = "Permissions", description = "API de gestion des permissions")
public class PermissionControllerImpl extends ControllerGenericImpl<PermissionRequest, PermissionReponse, Permission> implements PermissionController {
    protected PermissionControllerImpl(PermissionService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Permission newInstance() {
        return new Permission();
    }
}
