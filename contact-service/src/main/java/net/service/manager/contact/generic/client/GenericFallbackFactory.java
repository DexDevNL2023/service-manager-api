package net.service.manager.contact.generic.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GenericFallbackFactory implements FallbackFactory<Object> {

    @Override
    public Object create(Throwable cause) {
        return new GenericFallback(cause);
    }
}