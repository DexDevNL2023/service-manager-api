package net.service.manager.home.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.crud.services.SectionService;
import net.service.manager.home.generic.dto.request.BaseRequest;
import net.service.manager.home.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest extends BaseRequest {
    @NotBlank(message = "la cle est obligatoire")
    @UniqueValidator(service = SectionService.class, fieldName = "name", message = "la cle {} est déjà utilisé")
    private String key;
    private String target;
    @NotNull(message = "Le label est obligatoire")
    private String label;
    private String description;
    private SectionType type;
}
