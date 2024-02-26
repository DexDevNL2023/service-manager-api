package net.service.manager.contact.crud.controllers.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.service.manager.contact.crud.controllers.ContactController;
import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.crud.services.ContactService;
import net.service.manager.contact.generic.controller.impl.ControllerGenericImpl;
import net.service.manager.contact.rest.client.AuthorizationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contacts")
@Tag(name = "Contacts", description = "API de gestion des contacts")
public class ContactControllerImpl extends ControllerGenericImpl<ContactRequest, ContactReponse, Contact> implements ContactController {
    private final ContactService service;

    protected ContactControllerImpl(ContactService service, AuthorizationService authorizationService) {
        super(service, authorizationService);
        this.service = service;
    }

    @Override
    protected Contact newInstance() {
        return new Contact();
    }

    // Rest Client Controllers
    @Override
    @GetMapping("/find/{id}")
    @SecurityRequirement(name = "Authorization")
    public ContactReponse find(Long id) {
        return service.getOne(id);
    }

    @Override
    @GetMapping("/find/all")
    @SecurityRequirement(name = "Authorization")
    public List<ContactReponse> findAll() {
        return service.getAll();
    }
}
