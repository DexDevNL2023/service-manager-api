package net.service.manager.home.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutReponse {
    protected String numEnrg;
    private Long id;
    private String title;
    private String subTitle;
    private String icon;
    private String description;
    private List<String> images;
    private Boolean isVisible;
}
