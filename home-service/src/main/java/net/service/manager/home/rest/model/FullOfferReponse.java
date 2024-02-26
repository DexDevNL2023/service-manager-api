package net.service.manager.home.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullOfferReponse {
    protected String numEnrg;
    private Long id;
    private String name;
    private String description;
    private List<String> images;
    private PartnerReponse partenaire;
    private Boolean isVisible;
}
