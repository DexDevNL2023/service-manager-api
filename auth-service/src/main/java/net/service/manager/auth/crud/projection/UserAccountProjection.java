package net.service.manager.auth.crud.projection;

import net.service.manager.auth.crud.entities.UserAccount;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullUser", types = UserAccount.class)
public interface UserAccountProjection {
    Long getId();

    String getNumEnrg();

    String getDisplayName();

    String getContact();

    boolean getAdresse();

    String getLangKey();

    String getImageUrl();

    boolean getActived();

    boolean getConnected();
}