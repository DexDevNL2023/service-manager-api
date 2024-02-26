package net.service.manager.career.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.career.rest.enums.PartnerType;

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
