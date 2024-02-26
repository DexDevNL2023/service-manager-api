package net.service.manager.about.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.about.generic.dto.reponse.BaseReponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutReponse extends BaseReponse {
    private String title;
    private String subTitle;
    private String icon;
    private String description;
    private List<String> images;
    private Boolean isVisible;
}
