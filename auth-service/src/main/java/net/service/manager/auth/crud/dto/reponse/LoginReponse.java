package net.service.manager.auth.crud.dto.reponse;

import net.service.manager.auth.generic.dto.reponse.BaseReponse;
import net.service.manager.auth.crud.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReponse extends BaseReponse {
	private String displayName;
	private String lastName;
	private String firstName;
	private String accesToken;
	private String langKey;
	private String imageUrl;
	private RoleName role;

	public String getDisplayName() {
		return this.displayName = !this.firstName.isEmpty() ? this.lastName + " " + this.firstName : this.lastName;
	}
}
