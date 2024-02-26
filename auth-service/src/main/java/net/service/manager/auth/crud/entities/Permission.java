package net.service.manager.auth.crud.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.dto.request.PermissionRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions_utilisateurs")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "permissions_utilisateurs")
public class Permission extends BaseEntity<Permission, PermissionRequest> {

    private static final String ENTITY_NAME = "PERMISSION";

    private static final String MODULE_NAME = "AUTORISATIONS";

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Droit droit;

    private Boolean hasPermission;

    @Override
    public void update(Permission source) {
        this.role = source.getRole();
        this.droit = source.getDroit();
        this.hasPermission = source.getHasPermission();
    }

    @Override
    public boolean equalsToDto(PermissionRequest source) {
        if (source == null) {
            return false;
        }
        return role.getId().equals(source.getRoleId()) &&
                droit.getId().equals(source.getDroitId()) &&
                hasPermission.equals(source.getHasPermission());
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
