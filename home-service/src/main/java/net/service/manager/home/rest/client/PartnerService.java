package net.service.manager.home.rest.client;

import net.service.manager.home.generic.client.GenericFallbackFactory;
import net.service.manager.home.generic.config.FeignConfiguration;
import net.service.manager.home.rest.model.PartnerReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "partner-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface PartnerService {
    @GetMapping("/api/partners/find/{id}?projection=fullPartner")
    PartnerReponse findPartner(Long id);

    @GetMapping("/api/partners/find/all?projection=fullPartner")
    PagedModel<PartnerReponse> findAllPartner();
}
