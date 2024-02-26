package net.service.manager.career.crud.mapper;

import net.service.manager.career.crud.dto.reponse.CareerReponse;
import net.service.manager.career.crud.dto.request.CareerRequest;
import net.service.manager.career.crud.entities.Career;
import net.service.manager.career.generic.mapper.GenericMapper;

public interface CareerMapper extends GenericMapper<CareerRequest, CareerReponse, Career> {
}
