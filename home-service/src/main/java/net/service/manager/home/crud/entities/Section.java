package net.service.manager.home.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sections")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sections")
public class Section extends BaseEntity<Section, SectionRequest> {

    private static final String ENTITY_NAME = "SECTION";

    private static final String MODULE_NAME = "ACCUEIL";

    @Column(nullable = false, unique = true)
    private String key;
    private String target;
    private String label;
    private String description;
    @Enumerated(EnumType.STRING)
    private SectionType type;

    @Override
    public void update(Section source) {
        this.key = source.getKey();
        this.target = source.getTarget();
        this.label = source.getLabel();
    }

    @Override
    public boolean equalsToDto(SectionRequest source) {
        if (source == null) {
            return false;
        }
        return key.equals(source.getKey()) &&
                target.equals(source.getTarget()) &&
                label.equals(source.getLabel()) &&
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
