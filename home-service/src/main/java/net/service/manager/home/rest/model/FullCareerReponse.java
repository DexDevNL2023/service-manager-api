package net.service.manager.home.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.rest.enums.CareerType;

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
