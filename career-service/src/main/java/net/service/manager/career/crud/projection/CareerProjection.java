package net.service.manager.career.crud.projection;

import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.crud.enums.CareerType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCareer", types = Career.class)
public interface CareerProjection {
    Long getId();

    String getNumEnrg();

    String getJob();

    CareerType getType();

    String getDescription();

    String getMissions();

    String getJobRequirements();

    String getApplicantProfile();

    String getApplicationDocuments();

    String getAppyInstructions();

    String getDateLimite();

    String getHeureLimite();

    String getPartenaire();

    Boolean isVisible();
}