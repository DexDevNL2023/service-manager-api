package net.service.manager.home.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.rest.enums.ContactType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactReponse {
    protected String numEnrg;
    private Long id;
    private String name;
    private String description;
    private ContactType type;
    private String image;
    private Boolean isVisible;
}
