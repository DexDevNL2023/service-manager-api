package net.service.manager.career.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.career.crud.enums.CareerType;
import net.service.manager.career.generic.dto.request.BaseRequest;
import net.service.manager.career.generic.validators.enumaration.EnumValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerRequest extends BaseRequest {
    @NotBlank(message = "le poste est obligatoire")
    private String job;
    @EnumValidator(enumClass = CareerType.class)
    private CareerType type;
    @NotBlank(message = "la description est obligatoire")
    private String description;
    private String missions;
    private String jobRequirements;
    private String applicantProfile;
    @NotBlank(message = "la composition du dossier est obligatoire")
    private String applicationDocuments;
    @NotBlank(message = "Vous devez indiauer comment les candidats doivent postuler")
    private String appyInstructions;
    private String dateLimite;
    private String heureLimite;
    @Size(min = 1, message = "Vous devez la source de l'offre d'emploi")
    @NotNull(message = "Vous devez la source de l'offre d'emploi")
    private Long partnerId;
}
