package net.service.manager.partner.crud.projection;

import net.service.manager.partner.crud.entities.Partner;
import net.service.manager.partner.crud.enums.PartnerType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullPartner", types = Partner.class)
public interface PartnerProjection {
    Long getId();

    String getNumEnrg();

    String getName();

    String getSigle();

    PartnerType getType();

    String getAbout();

    String getEmail();

    String getTelephone();

    String getSiteWeb();

    String getBp();

    String getAdresse();

    String getVille();

    String getPays();

    Long getLatitude();

    Long getLongitude();

    String getLogo();

    String getContact();

    String getLocalization();

    Boolean isVisible();
}