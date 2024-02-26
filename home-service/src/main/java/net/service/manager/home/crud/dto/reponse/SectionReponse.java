package net.service.manager.home.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.generic.dto.reponse.BaseReponse;
import net.service.manager.home.rest.model.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionReponse extends BaseReponse {
    private String key;
    private String target;
    private String label;
    private String description;
    private SectionType type;
    private Boolean isVisible;
    private List<AboutReponse> abouts;
    private List<FullCareerReponse> careers;
    private List<ContactReponse> contacts;
    private List<FullOfferReponse> offers;
    private List<PartnerReponse> partners;
}
