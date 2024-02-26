package net.service.manager.about.crud.services;

import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.generic.service.ServiceGeneric;

public interface AboutService extends ServiceGeneric<AboutRequest, AboutReponse, About> {
}
