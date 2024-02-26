package net.service.manager.home.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.rest.model.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiteSectionReponse {
    private SectionType type;
    private AboutReponse about;
    private FullCareerReponse career;
    private ContactReponse contact;
    private FullOfferReponse offer;
    private PartnerReponse partner;

    public LiteSectionReponse(SectionType type) {
        this.type = type;
    }
}
