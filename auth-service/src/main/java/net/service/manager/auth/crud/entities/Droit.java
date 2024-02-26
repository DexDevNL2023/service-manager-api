package net.service.manager.auth.crud.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.auth.crud.dto.request.DroitRequest;
import net.service.manager.auth.generic.entity.audit.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "droits_utilisateurs")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "droits_utilisateurs")
public class Droit extends BaseEntity<Droit, DroitRequest> {

    private static final String ENTITY_NAME = "DROIT";

    private static final String MODULE_NAME = "AUTORISATIONS";

	@Column(nullable = false, unique = true)
	private String key;

	@Column(nullable = false)
	private String libelle;

	@Column(nullable = false)
	private String verbe;

	private String description;

    private Boolean isDefault;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Module module;

	@Override
	public void update(Droit source) {
		this.libelle = source.getLibelle();
		this.verbe = source.getVerbe();
		this.description = source.getDescription();
		this.isDefault = source.getIsDefault();
		this.module = source.getModule();
	}

	@Override
    public boolean equalsToDto(DroitRequest source) {
		if (source == null) {
			return false;
		}
		return libelle.equals(source.getLibelle()) &&
				verbe.equals(source.getVerbe()) &&
				description.equals(source.getDescription()) &&
				isDefault.equals(source.getIsDefault()) &&
                module.getId().equals(source.getModuleId());
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
