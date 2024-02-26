package net.service.manager.home.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.generic.entity.audit.BaseEntity;
import net.service.manager.home.generic.utils.AppConstants;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accueil")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "accueil")
public class Home extends BaseEntity<Home, HomeRequest> {

    private static final String ENTITY_NAME = "HOME";

    private static final String MODULE_NAME = "ACCUEIL";

    @Column(nullable = false, unique = true)
    private String linkUrl = AppConstants.WEB_URL;

    @Column(nullable = false, unique = true)
    private String name;
    private String hexaCouleurTheme;
    private String bannerImageUrl;
    private String bannerTitle;
    private String bannerDescription;
    private String logoUrl;
    private String faviconUrl;
    private String footerTitle;
    private String footerDescription;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Section> sections;

    @Override
    public void update(Home source) {
        this.name = source.getName();
        this.hexaCouleurTheme = source.getHexaCouleurTheme();
        this.logoUrl = source.getLogoUrl();
        this.faviconUrl = source.getFaviconUrl();
        this.sections = source.getSections();
    }

    @Override
    public boolean equalsToDto(HomeRequest source) {
        if (source == null) {
            return false;
        }
        return name.equals(source.getName()) &&
                hexaCouleurTheme.equals(source.getHexaCouleurTheme()) &&
                logoUrl.equals(source.getLogoUrl()) &&
                faviconUrl.equals(source.getFaviconUrl()) &&
                sections.equals(source.getSections());
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
