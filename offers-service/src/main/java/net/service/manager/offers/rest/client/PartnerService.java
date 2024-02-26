package net.service.manager.offers.rest.client;

import net.service.manager.offers.generic.client.GenericFallbackFactory;
import net.service.manager.offers.generic.config.FeignConfiguration;
import net.service.manager.offers.rest.model.PartnerReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "partner-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface PartnerService {
    @GetMapping("/api/partners/find/{id}?projection=fullPartner")
    PartnerReponse find(Long id);

    @GetMapping("/api/partners/find/all?projection=fullPartner")
    PagedModel<PartnerReponse> findAll();
}
