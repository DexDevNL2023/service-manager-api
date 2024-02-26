package net.service.manager.offers.crud.projection;

import net.service.manager.offers.crud.entities.Offer;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "fullOffer", types = Offer.class)
public interface OfferProjection {
    Long getId();

    String getNumEnrg();

    String getName();

    String getDescription();

    List<String> getImages();

    Boolean isVisible();
}