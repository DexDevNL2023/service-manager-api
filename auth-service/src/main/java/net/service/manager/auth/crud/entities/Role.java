package net.service.manager.auth.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.dto.request.RoleRequest;
import net.service.manager.auth.crud.enums.RoleName;
import net.service.manager.auth.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles_utilisateurs")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "roles_utilisateurs")
public class Role extends BaseEntity<Role, RoleRequest> {

    private static final String ENTITY_NAME = "ROLE";

    private static final String MODULE_NAME = "AUTORISATIONS";

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName libelle;

    private Boolean isSuper;

    @Override
    public void update(Role source) {
        this.libelle = source.getLibelle();
        this.isSuper = source.getIsSuper();
    }

    @Override
    public boolean equalsToDto(RoleRequest source) {
        if (source == null) {
            return false;
        }
        return libelle.equals(source.getLibelle()) &&
                isSuper.equals(source.getIsSuper());
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
