package net.service.manager.home.crud.services;

import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.reponse.LiteSectionReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.generic.service.ServiceGeneric;

public interface HomeService extends ServiceGeneric<HomeRequest, HomeReponse, Home> {
    HomeReponse getCurrentHomePage();

    LiteSectionReponse detailsSectionPage(Long sectionId, SectionType type);
}
