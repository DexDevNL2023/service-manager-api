package net.service.manager.partner.generic.dto.reponse;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseReponse {
    protected String numEnrg;
    private Long id;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}
