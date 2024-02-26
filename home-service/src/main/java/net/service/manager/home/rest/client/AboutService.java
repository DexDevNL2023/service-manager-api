package net.service.manager.home.rest.client;

import net.service.manager.home.generic.client.GenericFallbackFactory;
import net.service.manager.home.generic.config.FeignConfiguration;
import net.service.manager.home.rest.model.AboutReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "about-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface AboutService {
    @GetMapping("/api/abouts/find/{id}?projection=fullAbout")
    AboutReponse findAbout(Long id);

    @GetMapping("/api/abouts/find/all?projection=fullAbout")
    PagedModel<AboutReponse> findAllAbout();
}
