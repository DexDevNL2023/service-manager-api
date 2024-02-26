package net.service.manager.auth.crud.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.dto.request.UserRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;
import net.service.manager.auth.security.SecurityUtils;
import net.service.manager.auth.security.oauth2.users.OAuth2UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateurs")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "utilisateurs")
public class UserAccount extends BaseEntity<UserAccount, UserRequest> implements OAuth2User, OidcUser, Serializable {

    private static final String ENTITY_NAME = "UTILISATEUR";

    private static final String MODULE_NAME = "UTILISATEURS";

    @Serial
    private static final long serialVersionUID = -8551160985498051566L;

    @Transient
    private OidcIdToken idToken;

    @Transient
    private OidcUserInfo userInfo;

    @Transient
    private String displayName;

    @Transient
    private String contact;

    @Transient
    private String emailOrPhone;

    @Column(nullable = false, unique = true)
    private String lastName;

    private String firstName;

    private String email;

    private String phone;

    private String adresse;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String resetPasswordToken;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean actived;

    private boolean connected;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accesToken;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean usingQr;

    @Column(unique = true, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String loginUrl;

    private String langKey;

    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(  name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Map<String, Object> attributes;

    public void addAuthorities(Role authority) {
        this.roles.add(authority);
    }

    public UserAccount(String username, String email, String password, String phone, String adresse, Boolean actived) {
        this.displayName = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.adresse = adresse;
        this.actived = actived;
    }

    public UserAccount(String name, String email, OidcIdToken idToken2, OidcUserInfo userInfo2) {
        this.displayName = name;
        this.email = email;
        this.idToken = idToken2;
        this.userInfo = userInfo2;
    }

    public static UserAccount create(OAuth2UserInfo user, OidcIdToken idToken, OidcUserInfo userInfo) {
        UserAccount localUser = new UserAccount(user.getName(), user.getEmail(), idToken, userInfo);
        localUser.setAttributes(user.getAttributes());
        return localUser;
    }

    public String getDisplayName() {
        return this.displayName = !this.firstName.isEmpty() ? this.lastName + " " + this.firstName : this.lastName;
    }

    public String getContact() {
        return this.contact = !this.email.isEmpty() ? !this.phone.isEmpty() ? this.email + ", " + this.phone : this.email : this.phone;
    }

    public String getEmailOrPhone() {
        return this.emailOrPhone = this.email.isEmpty() ? this.phone : this.email;
    }

    @Override
    public String getName() {
        return this.getDisplayName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityUtils.buildUserAuthorities(this.getRoles());
    }

    @Override
    public void update(UserAccount source) {
        this.lastName = source.getLastName();
        this.firstName = source.getFirstName();
        this.email = source.getEmail();
        this.phone = source.getPhone();
        this.adresse = source.getAdresse();
        this.connected = source.isConnected();
        this.usingQr = source.isUsingQr();
        this.loginUrl = source.getLoginUrl();
        this.langKey = source.getLangKey();
        this.imageUrl = source.getImageUrl();
        this.roles = source.getRoles();
    }

    @Override
    public boolean equalsToDto(UserRequest source) {
        if (source == null) {
            return false;
        }
        return lastName.equals(source.getLastName()) &&
                firstName.equals(source.getFirstName()) &&
                email.equals(source.getEmail()) &&
                phone.equals(source.getPhone()) &&
                adresse.equals(source.getAdresse());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }
}