package net.service.manager.home.rest.client;

import net.service.manager.home.generic.client.GenericFallbackFactory;
import net.service.manager.home.generic.config.FeignConfiguration;
import net.service.manager.home.rest.model.FullOfferReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "offers-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface OfferService {
    @GetMapping("/api/offers/find/{id}?projection=fullOffer")
    FullOfferReponse findOffer(Long id);

    @GetMapping("/api/offers/find/all?projection=fullOffer")
    PagedModel<FullOfferReponse> findAllOffers();
}
