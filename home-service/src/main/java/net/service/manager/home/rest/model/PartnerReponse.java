package net.service.manager.home.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.rest.enums.PartnerType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerReponse {
    protected String numEnrg;
    private Long id;
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
