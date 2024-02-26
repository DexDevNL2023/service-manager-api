package net.service.manager.home.crud.services;

import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.generic.service.ServiceGeneric;

public interface SectionService extends ServiceGeneric<SectionRequest, SectionReponse, Section> {
}
