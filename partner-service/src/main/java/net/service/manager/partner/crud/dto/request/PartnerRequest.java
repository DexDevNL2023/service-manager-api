package net.service.manager.partner.crud.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.partner.crud.enums.PartnerType;
import net.service.manager.partner.crud.services.PartnerService;
import net.service.manager.partner.generic.dto.request.BaseRequest;
import net.service.manager.partner.generic.validators.enumaration.EnumValidator;
import net.service.manager.partner.generic.validators.unique.UniqueValidator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerRequest extends BaseRequest {
    @NotBlank(message = "le nom est obligatoire")
    @UniqueValidator(service = PartnerService.class, fieldName = "name", message = "Le nom {} est déjà utilisé")
    private String name;
    @NotBlank(message = "le sigle de l'institution est obligatoire")
    private String sigle;
    private String about;
    @EnumValidator(enumClass = PartnerType.class)
    private PartnerType type;
    @NotBlank(message = "l'email est obligatoire")
    @Email(message = "le format de l'email est incorrecte", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotBlank(message = "le telephone est obligatoire")
    private String telephone;
    private String siteWeb;
    private String bp;
    @NotBlank(message = "l'adresse est obligatoire")
    private String adresse;
    @NotBlank(message = "la ville est obligatoire")
    private String ville;
    @NotBlank(message = "le pays est obligatoire")
    private String pays;
    private Float latitude;
    private Float longitude;
    private String logo;
}
