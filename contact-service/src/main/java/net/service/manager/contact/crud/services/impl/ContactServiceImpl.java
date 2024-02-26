package net.service.manager.contact.crud.services.impl;

import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.crud.mapper.ContactMapper;
import net.service.manager.contact.crud.repositories.ContactRepository;
import net.service.manager.contact.crud.repositories.ContactSearchRepository;
import net.service.manager.contact.crud.services.ContactService;
import net.service.manager.contact.generic.service.impl.ServiceGenericImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactServiceImpl extends ServiceGenericImpl<ContactRequest, ContactReponse, Contact> implements ContactService {
    public ContactServiceImpl(JpaEntityInformation<Contact, Long> entityInformation, ContactRepository repository, ContactSearchRepository searchRepository, ContactMapper mapper) {
        super(entityInformation, repository, searchRepository, mapper);
    }
}
