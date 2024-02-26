package net.service.manager.auth.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.generic.dto.reponse.BaseReponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReponse extends BaseReponse {
    private String displayName;
    private String email;
    private String contact;
    private String langKey;
    private String imageUrl;
    private boolean actived;
    private boolean connected;
    private List<RoleReponse> roles;
}
