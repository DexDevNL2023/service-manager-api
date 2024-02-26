package net.service.manager.auth.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.services.DroitService;
import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroitRequest extends BaseRequest {
    @NotBlank(message = "La clé du droit est obligatoire")
    @Size(min = 2, message = "La clé doit être d'au moins 2 caractères")
    @UniqueValidator(service = DroitService.class, fieldName = "key", message = "La clé {} est déjà utilisé")
    private String key;
    @NotBlank(message = "le libelle du droit est obligatoire")
    private String libelle;
    @NotBlank(message = "La verbe du droit est obligatoire")
    private String verbe;
    private String description;
    private Long moduleId;
    private Boolean isDefault = false;
}
