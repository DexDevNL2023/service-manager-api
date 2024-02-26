package net.service.manager.about.crud.controllers.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.about.crud.controllers.AboutController;
import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.crud.services.AboutService;
import net.service.manager.about.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.about.rest.client.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/abouts")
@Tag(name = "Abouts", description = "API de gestion des apropos")
public class AboutControllerImpl extends ControllerGenericImpl<AboutRequest, AboutReponse, About> implements AboutController {
    private final AboutService service;

    protected AboutControllerImpl(AboutService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
        this.service = service;
    }

    @Override
    protected About newInstance() {
        return new About();
    }

    // Rest Client Controllers
    @Override
    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "Authorization")
    public AboutReponse find(Long id) {
        return service.getOne(id);
    }

    @Override
    @GetMapping("/find/all")
    @SecurityRequirement(name = "Authorization")
    public List<AboutReponse> findAll() {
        return service.getAll();
    }
}
