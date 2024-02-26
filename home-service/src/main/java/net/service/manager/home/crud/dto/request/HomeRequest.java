package net.service.manager.home.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.crud.services.HomeService;
import net.service.manager.home.generic.dto.request.BaseRequest;
import net.service.manager.home.generic.validators.unique.UniqueValidator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeRequest extends BaseRequest {
    @NotBlank(message = "le nom du site est obligatoire")
    @UniqueValidator(service = HomeService.class, fieldName = "name", message = "Le nom du site {} est déjà utilisé")
    private String name;
    private String hexaCouleurTheme;
    private String bannerImageUrl;
    private String bannerTitle;
    private String bannerDescription;
    private String logoUrl;
    private String faviconUrl;
    private String footerTitle;
    private String footerDescription;
    @Size(min = 1, message = "Vous devez ajouter au moins une section")
    @NotNull(message = "Vous devez ajouter au moins une section")
    private List<SectionType> sections;
}
