package net.service.manager.partner.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.partner.crud.dto.request.PartnerRequest;
import net.service.manager.partner.crud.enums.PartnerType;
import net.service.manager.partner.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partenaires")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "partenaires")
public class Partner extends BaseEntity<Partner, PartnerRequest> {

    private static final String ENTITY_NAME = "PARTNER";

    private static final String MODULE_NAME = "PARTENAIRES";

    @Transient
    private String contact;

    @Transient
    private String localization;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String sigle;

    private String about;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    private String siteWeb;

    private String bp;

    private String adresse;

    private String ville;

    private String pays;

    private Float latitude;
    private Float longitude;

    private String logo;

    @Override
    public void update(Partner source) {
        this.name = source.getName();
        this.sigle = source.getSigle();
        this.type = source.getType();
        this.about = source.getAbout();
        this.email = source.getEmail();
        this.telephone = source.getTelephone();
        this.siteWeb = source.getSiteWeb();
        this.bp = source.getBp();
        this.adresse = source.getAdresse();
        this.ville = source.getVille();
        this.pays = source.getPays();
        this.latitude = source.getLatitude();
        this.longitude = source.getLongitude();
        this.logo = source.getLogo();
    }

    @Override
    public boolean equalsToDto(PartnerRequest source) {
        if (source == null) {
            return false;
        }
        return name.equals(source.getName()) &&
                sigle.equals(source.getSigle()) &&
                type.equals(source.getType()) &&
                about.equals(source.getAbout()) &&
                email.equals(source.getEmail()) &&
                telephone.equals(source.getTelephone()) &&
                siteWeb.equals(source.getSiteWeb()) &&
                bp.equals(source.getBp()) &&
                adresse.equals(source.getAdresse()) &&
                ville.equals(source.getVille()) &&
                pays.equals(source.getPays()) &&
                latitude.equals(source.getLatitude()) &&
                longitude.equals(source.getLongitude()) &&
                logo.equals(source.getLogo());
    }

    public String getContact() {
        this.contact = "";
        this.contact += !this.email.isEmpty() ? !this.telephone.isEmpty() ? this.email + "/" + this.telephone : this.email : this.telephone;
        return this.contact;
    }

    public String getLocalization() {
        this.localization = "";
        this.localization += !this.bp.isEmpty() ? this.bp : "";
        this.localization += !this.adresse.isEmpty() ? !this.localization.isEmpty() ? " " + this.adresse : this.adresse : "";
        this.localization += !this.ville.isEmpty() ? !this.localization.isEmpty() ? ", " + this.ville : this.ville : "";
        this.localization += !this.pays.isEmpty() ? !this.localization.isEmpty() ? " - " + this.pays : this.pays : "";
        return this.localization;
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }
}
