package net.service.manager.contact.crud.controllers;

import jakarta.validation.constraints.NotNull;
import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.generic.controller.ControllerGeneric;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ContactController extends ControllerGeneric<ContactRequest, ContactReponse, Contact> {

    // Rest Client Controllers
    ContactReponse find(@NotNull @PathVariable("id") Long id);

    List<ContactReponse> findAll();
}
