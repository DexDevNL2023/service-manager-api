package net.service.manager.about.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.about.crud.services.AboutService;
import net.service.manager.about.generic.dto.request.BaseRequest;
import net.service.manager.about.generic.validators.unique.UniqueValidator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutRequest extends BaseRequest {
    @NotBlank(message = "le titre est obligatoire")
    @UniqueValidator(service = AboutService.class, fieldName = "title", message = "Le titre {} est déjà utilisé")
    private String title;
    private String subTitle;
    private String icon;
    private String description;
    @Size(min = 1, message = "Vous devez ajouter au moins une image")
    @NotNull(message = "Vous devez ajouter au moins une image")
    private List<String> images;
}
