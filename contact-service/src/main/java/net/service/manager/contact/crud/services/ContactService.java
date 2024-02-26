package net.service.manager.contact.crud.services;

import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.generic.service.ServiceGeneric;

public interface ContactService extends ServiceGeneric<ContactRequest, ContactReponse, Contact> {
}
