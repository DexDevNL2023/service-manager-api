package net.service.manager.contact.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.contact.crud.enums.ContactType;
import net.service.manager.contact.crud.services.ContactService;
import net.service.manager.contact.generic.dto.request.BaseRequest;
import net.service.manager.contact.generic.validators.enumaration.EnumValidator;
import net.service.manager.contact.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest extends BaseRequest {
    @NotBlank(message = "l'image est obligatoire")
    String image;
    @NotBlank(message = "le nom est obligatoire")
    @UniqueValidator(service = ContactService.class, fieldName = "name", message = "Le nom {} est déjà utilisé")
    private String name;
    @NotBlank(message = "la description est obligatoire")
    private String description;
    @EnumValidator(enumClass = ContactType.class)
    private ContactType type;
}
