package net.service.manager.contact.crud.projection;

import net.service.manager.contact.crud.entities.Contact;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullContact", types = Contact.class)
public interface ContactProjection {
    Long getId();

    String getNumEnrg();

    String getName();

    String getDescription();

    String getImage();

    Boolean isVisible();
}