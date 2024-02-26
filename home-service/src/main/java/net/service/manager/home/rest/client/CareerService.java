package net.service.manager.home.rest.client;

import net.service.manager.home.generic.client.GenericFallbackFactory;
import net.service.manager.home.generic.config.FeignConfiguration;
import net.service.manager.home.rest.model.FullCareerReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "career-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface CareerService {
    @GetMapping("/api/careers/find/{id}?projection=fullCareer")
    FullCareerReponse findCareer(Long id);

    @GetMapping("/api/careers/find/all?projection=fullCareer")
    PagedModel<FullCareerReponse> findAllCareer();
}
