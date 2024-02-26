package net.service.manager.offers.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.offers.crud.services.OfferService;
import net.service.manager.offers.generic.dto.request.BaseRequest;
import net.service.manager.offers.generic.validators.unique.UniqueValidator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRequest extends BaseRequest {
    @NotBlank(message = "le nom est obligatoire")
    @UniqueValidator(service = OfferService.class, fieldName = "name", message = "Le nom {} est déjà utilisé")
    private String name;
    private String description;
    @Size(min = 1, message = "Vous devez ajouter au moins une image")
    @NotNull(message = "Vous devez ajouter au moins une image")
    private List<String> images;
    @Size(min = 1, message = "Vous devez la source de l'offre d'emploi")
    @NotNull(message = "Vous devez la source de l'offre d'emploi")
    private Long partnerId;
}
