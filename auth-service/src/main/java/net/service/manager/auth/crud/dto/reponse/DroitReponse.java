package net.service.manager.auth.crud.dto.reponse;

import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroitReponse extends BaseReponse {
    private Long id;
    private String libelle;
    private String key;
    private String verbe;
    private Boolean isDefault;
    private String description;
    private ModuleReponse module;
}
