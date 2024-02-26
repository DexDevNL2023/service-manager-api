package net.service.manager.auth.crud.dto.request;

import net.service.manager.auth.generic.dto.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest extends BaseRequest {
    private String emailOrPhone;
    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String adresse;
    private boolean usingQr = false;
    private String langKey;
    @Size(max = 256, message = "La taille de l'image doit être inférieur ou égale à 256")
    private String imageUrl;
    private List<Long> roles;

    public String getEmailOrPhone() {
        return this.emailOrPhone = this.email.isEmpty() ? this.phone : this.email;
    }
}
