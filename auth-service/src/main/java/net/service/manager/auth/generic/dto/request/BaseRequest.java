package net.service.manager.auth.generic.dto.request;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseRequest {
    private Long id;
}
