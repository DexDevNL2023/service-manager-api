package net.service.manager.contact.crud.repositories;

import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.generic.repository.search.GenericSearchRepository;

public interface ContactSearchRepository extends GenericSearchRepository<ContactRequest, Contact> {
}
