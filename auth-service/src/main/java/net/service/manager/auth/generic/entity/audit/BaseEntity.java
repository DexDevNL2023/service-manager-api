package net.service.manager.auth.generic.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import net.service.manager.auth.generic.entity.GenericEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "createdAt", "updatedBy", "updatedAt"},
        allowGetters = true
)
public abstract class BaseEntity<E, D> implements GenericEntity<E, D>, Serializable {
    @Serial
    private static final long serialVersionUID = -8551160985498051566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true, nullable = false, updatable = false)
    protected String numEnrg;
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "deleted_at")
    private Instant deletedAt;
    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    @Column(name = "is_visible")
    private Boolean isVisible = false;

    @PreUpdate
    @PrePersist
    public void beforeAnyUpdate() {
        if (isDeleted) {
            if (deletedBy == null) {
                deletedBy = updatedBy;
            }

            if (deletedAt == null) {
                deletedAt = updatedAt;
            }
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNumEnrg() {
        return numEnrg;
    }

    @Override
    public void setNumEnrg(String numEnrg) {
        this.numEnrg = numEnrg;
    }

    @Override
    public Boolean isDeleted() {
        return isDeleted;
    }

    @Override
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public Boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setIsVisibled(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}
