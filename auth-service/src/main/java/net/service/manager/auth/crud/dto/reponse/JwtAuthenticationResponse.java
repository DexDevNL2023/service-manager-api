package net.service.manager.auth.crud.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {
	private String accessToken;
	private boolean authenticated;
	private LoginReponse user;
}