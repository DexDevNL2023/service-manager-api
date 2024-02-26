package net.service.manager.auth.security.oauth2;

import net.service.manager.auth.crud.services.UserAccountService;
import net.service.manager.auth.generic.exceptions.OAuth2AuthenticationProcessingException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends OidcUserService {

	private final UserAccountService userAccountService;

	public CustomOidcUserService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		try {
			return userAccountService.processOAuthRegister(userRequest.getClientRegistration().getRegistrationId(), oidcUser.getAttributes(), oidcUser.getIdToken(), oidcUser.getUserInfo());
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Lancer une instance de AuthenticationException déclenchera OAuth2AuthenticationFailureHandler
			throw new OAuth2AuthenticationProcessingException(ex.getMessage(), ex.getCause());
		}
	}
}