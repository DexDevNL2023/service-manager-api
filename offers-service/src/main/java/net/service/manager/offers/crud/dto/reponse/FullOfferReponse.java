package net.service.manager.offers.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.offers.rest.model.PartnerReponse;

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
