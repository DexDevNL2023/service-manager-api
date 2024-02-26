package net.service.manager.home.rest.client;

import net.service.manager.home.generic.client.GenericFallbackFactory;
import net.service.manager.home.generic.config.FeignConfiguration;
import net.service.manager.home.rest.model.ContactReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "contact-service", configuration = FeignConfiguration.class, fallbackFactory = GenericFallbackFactory.class)
public interface ContactService {
    @GetMapping("/api/contacts/find/{id}?projection=fullContact")
    ContactReponse findContact(Long id);

    @GetMapping("/api/contacts/find/all?projection=fullContact")
    PagedModel<ContactReponse> findAllContact();
}
