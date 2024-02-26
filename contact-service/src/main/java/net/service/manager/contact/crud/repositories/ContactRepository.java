package net.service.manager.contact.crud.repositories;

import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.generic.repository.dao.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends GenericRepository<ContactRequest, Contact> {
}
