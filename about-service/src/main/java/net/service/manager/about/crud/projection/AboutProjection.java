package net.service.manager.about.crud.projection;

import net.service.manager.about.crud.entities.About;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "fullAbout", types = About.class)
public interface AboutProjection {
    Long getId();

    String getNumEnrg();

    String getTitle();

    String getSubTitle();

    String getIcon();

    String getDescription();

    List<String> getImages();

    Boolean isVisible();
}