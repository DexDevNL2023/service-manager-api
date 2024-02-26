package net.service.manager.home.crud.controllers;

import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.generic.controller.ControllerGeneric;

public interface HomeController extends ControllerGeneric<HomeRequest, HomeReponse, Home> {
}
