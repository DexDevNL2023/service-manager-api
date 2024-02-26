package net.service.manager.auth.crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.dto.request.ModuleRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "modules_applications")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "modules_applications")
public class Module extends BaseEntity<Module, ModuleRequest> {

    private static final String ENTITY_NAME = "MODULE";

    private static final String MODULE_NAME = "AUTORISATIONS";

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Override
    public void update(Module source) {
        this.name = source.getName();
        this.description = source.getDescription();
    }

    @Override
    public boolean equalsToDto(ModuleRequest source) {
        if (source == null) {
            return false;
        }
        return name.equals(source.getName()) &&
                description.equals(source.getDescription());
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
