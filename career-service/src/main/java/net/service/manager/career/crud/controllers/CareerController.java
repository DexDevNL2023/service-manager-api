package net.service.manager.career.crud.controllers;

import jakarta.validation.constraints.NotNull;
import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.reponse.FullCareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.generic.controller.ControllerGeneric;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CareerController extends ControllerGeneric<CareerRequest, CareerReponse, Career> {

    // Rest Client Controllers
    FullCareerReponse find(@NotNull @PathVariable("id") Long id);

    List<FullCareerReponse> findAll();
}
