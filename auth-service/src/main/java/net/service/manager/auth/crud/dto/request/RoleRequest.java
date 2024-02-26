package net.service.manager.auth.crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.crud.services.RoleService;
import net.service.manager.auth.generic.dto.request.BaseRequest;
import net.service.manager.auth.generic.validators.enumaration.EnumValidator;
import net.service.manager.auth.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest extends BaseRequest {
    @EnumValidator(enumClass = RoleName.class)
    @UniqueValidator(service = RoleService.class, fieldName = "libelle", message = "Le libelle {} est déjà utilisé")
    private RoleName libelle;
    private Boolean isSuper = false;
}
