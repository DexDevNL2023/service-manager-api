package net.service.manager.offers.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.offers.rest.enums.PartnerType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerReponse {
    protected String numEnrg;
    private Long id;
    private String name;
    private String sigle;
    private String avantPropos;
    private PartnerType type;
    private String contact;
    private String siteWeb;
    private String localization;
    private Float latitude;
    private Float longitude;
    private String logo;
    private Boolean isVisible;
}
