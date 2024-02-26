package net.service.manager.auth.crud.dto.reponse;

import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import net.service.manager.auth.crud.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleReponse extends BaseReponse {
    private RoleName libelle;
    private Boolean isSuper;
    private List<PermissionReponse> permissions;
}
