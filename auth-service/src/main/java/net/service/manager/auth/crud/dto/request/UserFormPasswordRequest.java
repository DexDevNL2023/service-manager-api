package net.service.manager.auth.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.generic.validators.password.PasswordMatches;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserFormPasswordRequest {
    @NotBlank(message = "Veillez renseignez votre login svp!")
    private String emailOrPhone;
    @NotBlank(message = "Veillez renseigner votre ancien mot de passe svp!")
    private String lastPassword;
    @NotBlank(message = "Veillez indiquer votre nouveau mot de passe svp!")
    private String newPassword;
    @NotBlank(message = "Le mot de passe de confirmation est obligatoire")
    private String matchingPassword;
}
