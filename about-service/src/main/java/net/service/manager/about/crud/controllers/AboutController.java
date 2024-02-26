package net.service.manager.about.crud.controllers;

import jakarta.validation.constraints.NotNull;
import net.service.manager.about.crud.dto.reponse.AboutReponse;
import net.service.manager.about.crud.dto.request.AboutRequest;
import net.service.manager.about.crud.entities.About;
import net.service.manager.about.generic.controller.ControllerGeneric;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AboutController extends ControllerGeneric<AboutRequest, AboutReponse, About> {

    // Rest Client Controllers
    AboutReponse find(@NotNull @PathVariable("id") Long id);

    List<AboutReponse> findAll();
}
