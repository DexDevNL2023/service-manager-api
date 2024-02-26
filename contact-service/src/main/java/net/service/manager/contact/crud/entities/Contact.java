package net.service.manager.contact.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.contact.crud.dto.request.ContactRequest;
import net.service.manager.contact.crud.enums.ContactType;
import net.service.manager.contact.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "contacts")
public class Contact extends BaseEntity<Contact, ContactRequest> {

    private static final String ENTITY_NAME = "CONTACT";

    private static final String MODULE_NAME = "CONTACTS";

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    String image;

    @Override
    public void update(Contact source) {
        this.name = source.getName();
        this.description = source.getDescription();
        this.type = source.getType();
        this.image = source.getImage();
    }

    @Override
    public boolean equalsToDto(ContactRequest source) {
        if (source == null) {
            return false;
        }
        return name.equals(source.getName()) &&
                description.equals(source.getDescription()) &&
                type.equals(source.getType()) &&
                image.equals(source.getImage());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }
}
