package net.service.manager.partner.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.partner.crud.enums.PartnerType;
import net.service.manager.partner.generic.dto.reponse.BaseReponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerReponse extends BaseReponse {
    private String contact;
    private String localization;
    private String name;
    private String sigle;
    private String about;
    private PartnerType type;
    private String email;
    private String telephone;
    private String siteWeb;
    private String bp;
    private String adresse;
    private String ville;
    private String pays;
    private Float latitude;
    private Float longitude;
    private String logo;
    private Boolean isVisible;
}
