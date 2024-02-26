package net.service.manager.contact.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.contact.crud.enums.ContactType;
import net.service.manager.contact.generic.dto.reponse.BaseReponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactReponse extends BaseReponse {
    private String name;
    private String description;
    private String image;
    private ContactType type;
    private Boolean isVisible;
}
