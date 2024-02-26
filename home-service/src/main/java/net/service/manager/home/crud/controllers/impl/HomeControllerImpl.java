package net.service.manager.home.crud.controllers.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.home.crud.controllers.HomeController;
import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.crud.services.HomeService;
import net.service.manager.home.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.home.rest.client.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/homes")
@Tag(name = "Homes", description = "API de gestion de l'accueil")
public class HomeControllerImpl extends ControllerGenericImpl<HomeRequest, HomeReponse, Home> implements HomeController {

    protected HomeControllerImpl(HomeService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
    }

    @Override
    protected Home newInstance() {
        return new Home();
    }
}
