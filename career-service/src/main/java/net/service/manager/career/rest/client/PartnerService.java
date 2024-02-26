package net.service.manager.career.rest.client;

import net.service.manager.career.generic.client.GenericFallbackFactory;
import net.service.manager.career.generic.config.FeignConfiguration;
import net.service.manager.career.rest.model.PartnerReponse;
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
