package net.service.manager.auth.security.oauth2.users;

import net.service.manager.auth.crud.enums.SocialProvider;
import net.service.manager.auth.generic.exceptions.OAuth2AuthenticationProcessingException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OAuth2UserInfoFactory {
	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(SocialProvider.GOOGLE.getValue())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.FACEBOOK.getValue())) {
			return new FacebookOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.GITHUB.getValue())) {
			return new GithubOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.LINKEDIN.getValue())) {
			return new LinkedinOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.TWITTER.getValue())) {
			return new GithubOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException("Désolé! la connexion avec " + registrationId + " n'est pas encore pris en charge.");
		}
	}
}