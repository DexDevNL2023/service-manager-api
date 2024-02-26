package net.service.manager.auth.crud.dto.reponse;

import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleReponse extends BaseReponse {
    private String name;
    private String description;
}
