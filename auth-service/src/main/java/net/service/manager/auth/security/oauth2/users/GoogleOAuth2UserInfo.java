package net.service.manager.auth.security.oauth2.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

	public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("sub");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return (String) attributes.get("picture");
	}
}