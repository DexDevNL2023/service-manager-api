package net.service.manager.contact.crud.mapper.impl;

import net.service.manager.contact.crud.dto.reponse.ContactReponse;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.entities.Contact;
import net.service.manager.contact.crud.mapper.ContactMapper;
import net.service.manager.contact.crud.repositories.ContactRepository;
import net.service.manager.contact.generic.mapper.impl.GenericMapperImpl;
import org.mapstruct.Mapper;

@Mapper
public class ContactMapperImpl extends GenericMapperImpl<ContactRequest, ContactReponse, Contact> implements ContactMapper {

    protected ContactMapperImpl(Class<Contact> entityClass, Class<ContactReponse> dtoClass, ContactRepository repository) {
        super(entityClass, dtoClass, repository);
    }

    @Override
    protected Contact newInstance() {
        return new Contact();
    }
}
