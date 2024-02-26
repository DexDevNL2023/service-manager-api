package net.service.manager.home.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.generic.dto.reponse.BaseReponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeReponse extends BaseReponse {
    private String linkUrl;
    private String name;
    private String hexaCouleurTheme;
    private String bannerImageUrl;
    private String bannerTitle;
    private String bannerDescription;
    private String logoUrl;
    private String faviconUrl;
    private String footerTitle;
    private String footerDescription;
    private List<SectionReponse> sections;
    private Boolean isVisible;
}
