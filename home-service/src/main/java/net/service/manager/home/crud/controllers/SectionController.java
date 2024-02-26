package net.service.manager.home.crud.controllers;

import net.service.manager.home.crud.dto.reponse.SectionReponse;
import net.service.manager.home.crud.dto.request.SectionRequest;
import net.service.manager.home.crud.entities.Section;
import net.service.manager.home.generic.controller.ControllerGeneric;

public interface SectionController extends ControllerGeneric<SectionRequest, SectionReponse, Section> {
}
