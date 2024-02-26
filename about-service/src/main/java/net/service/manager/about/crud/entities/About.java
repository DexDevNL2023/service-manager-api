package net.service.manager.about.crud.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.generic.entity.audit.BaseEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apropos")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "apropos")
public class About extends BaseEntity<About, AboutRequest> {

    private static final String ENTITY_NAME = "ABOUT";

    private static final String MODULE_NAME = "APROPOS";

    @Column(nullable = false, unique = true)
    private String title;

    private String subTitle;
    private String icon;
    private String description;

    @ElementCollection
    @CollectionTable(name = "About_Images", joinColumns = @JoinColumn(name = "about_id", nullable = false))
    @Column(name = "image_url", nullable = false)
    private List<String> images;

    @Override
    public void update(About source) {
        this.title = source.getTitle();
        this.subTitle = source.getSubTitle();
        this.icon = source.getIcon();
        this.description = source.getDescription();
        this.images = source.getImages();
    }

    @Override
    public boolean equalsToDto(AboutRequest source) {
        if (source == null) {
            return false;
        }
        return title.equals(source.getTitle()) &&
                subTitle.equals(source.getSubTitle()) &&
                icon.equals(source.getIcon()) &&
                description.equals(source.getDescription()) &&
                images.equals(source.getImages());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }
}
