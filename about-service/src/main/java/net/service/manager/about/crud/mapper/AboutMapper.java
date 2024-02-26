package net.service.manager.about.crud.mapper;

import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.generic.mapper.GenericMapper;

public interface AboutMapper extends GenericMapper<AboutRequest, AboutReponse, About> {
}
