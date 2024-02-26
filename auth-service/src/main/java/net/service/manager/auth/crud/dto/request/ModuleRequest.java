package net.service.manager.auth.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.services.ModuleService;
import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest extends BaseRequest {
    @NotBlank(message = "le nom du module est obligatoire")
    @UniqueValidator(service = ModuleService.class, fieldName = "name", message = "Le nom {} est déjà utilisé")
    private String name;
    private String description;
}
