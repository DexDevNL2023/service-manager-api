package net.service.manager.career.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.career.crud.enums.CareerType;
import net.service.manager.career.rest.model.PartnerReponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullCareerReponse {
    protected String numEnrg;
    private Long id;
    private String job;
    private CareerType type;
    private String description;
    private String missions;
    private String jobRequirements;
    private String applicantProfile;
    private String applicationDocuments;
    private String appyInstructions;
    private String dateLimite;
    private String heureLimite;
    private PartnerReponse partenaire;
    private Boolean isVisible;
}
