package net.service.manager.home.crud.mapper;

import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.generic.mapper.GenericMapper;

public interface HomeMapper extends GenericMapper<HomeRequest, HomeReponse, Home> {
}
