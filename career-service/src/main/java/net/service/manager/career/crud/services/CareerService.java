package net.service.manager.career.crud.services;

import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.generic.service.ServiceGeneric;

import java.util.List;

public interface CareerService extends ServiceGeneric<CareerRequest, CareerReponse, Career> {
    Career findOne(Long id);

    List<Career> findAll();
}
