package net.service.manager.home.generic.dto.request;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseRequest {
    private Long id;
}
