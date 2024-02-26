package net.service.manager.home.crud.mapper;

import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.generic.mapper.GenericMapper;

public interface SectionMapper extends GenericMapper<SectionRequest, SectionReponse, Section> {
}
