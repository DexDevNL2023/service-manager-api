package net.service.manager.career.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.enums.CareerType;
import net.service.manager.career.generic.entity.audit.BaseEntity;
import net.service.manager.career.rest.model.PartnerReponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offres_emplois")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "offres_emplois")
public class Career extends BaseEntity<Career, CareerRequest> {

    private static final String ENTITY_NAME = "CAREER";

    private static final String MODULE_NAME = "CARRIERES";

    private String job;

    @Enumerated(EnumType.STRING)
    private CareerType type;

    private String description;

    private String missions;

    private String jobRequirements;

    private String applicantProfile;

    private String applicationDocuments;

    private String appyInstructions;

    private String dateLimite;

    private String heureLimite;

    @Column(name = "partner_id", nullable = false, updatable = false)
    private Long partnerId;

    @Transient
    private PartnerReponse partenaire;

    @Override
    public void update(Career source) {
        this.job = source.getJob();
        this.type = source.getType();
        this.description = source.getDescription();
        this.missions = source.getMissions();
        this.jobRequirements = source.getJobRequirements();
        this.applicantProfile = source.getApplicantProfile();
        this.applicationDocuments = source.getApplicationDocuments();
        this.appyInstructions = source.getAppyInstructions();
        this.dateLimite = source.getDateLimite();
        this.heureLimite = source.getHeureLimite();
    }

    @Override
    public boolean equalsToDto(CareerRequest source) {
        if (source == null) {
            return false;
        }
        return job.equals(source.getJob()) &&
                type.equals(source.getType()) &&
                description.equals(source.getDescription()) &&
                missions.equals(source.getMissions()) &&
                jobRequirements.equals(source.getJobRequirements()) &&
                applicantProfile.equals(source.getApplicantProfile()) &&
                applicationDocuments.equals(source.getApplicationDocuments()) &&
                appyInstructions.equals(source.getAppyInstructions()) &&
                dateLimite.equals(source.getDateLimite()) &&
                heureLimite.equals(source.getHeureLimite()) &&
                partnerId.equals(source.getPartnerId());
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
