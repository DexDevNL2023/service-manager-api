package net.service.manager.offers.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.offers.crud.dto.request.OfferRequest;
import net.service.manager.offers.generic.entity.audit.BaseEntity;
import net.service.manager.offers.rest.model.PartnerReponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services_offerts")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "services_offerts")
public class Offer extends BaseEntity<Offer, OfferRequest> {

    private static final String ENTITY_NAME = "OFFERS";

    private static final String MODULE_NAME = "SERVICES";

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ElementCollection
    @CollectionTable(name = "Offers_Images", joinColumns = @JoinColumn(name = "offers_id", nullable = false))
    @Column(name = "image_url", nullable = false)
    private List<String> images;

    @Column(name = "partner_id", nullable = false, updatable = false)
    private Long partnerId;

    @Transient
    private PartnerReponse partenaire;

    @Override
    public void update(Offer source) {
        this.name = source.getName();
        this.description = source.getDescription();
        this.images = source.getImages();
    }

    @Override
    public boolean equalsToDto(OfferRequest source) {
        if (source == null) {
            return false;
        }
        return name.equals(source.getName()) &&
                description.equals(source.getDescription()) &&
                images.equals(source.getImages()) &&
                partnerId.equals(source.getPartnerId());
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
