package net.service.manager.contact.crud.mapper;

import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.generic.mapper.GenericMapper;

public interface ContactMapper extends GenericMapper<ContactRequest, ContactReponse, Contact> {
}
