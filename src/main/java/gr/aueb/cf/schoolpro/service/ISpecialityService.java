package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolpro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolpro.model.Speciality;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ISpecialityService {

    Speciality insertSpeciality(SpecialityInsertDTO specialityInsertDTO) throws Exception;
    Speciality updateSpeciality(SpecialityUpdateDTO specialityUpdateDTO) throws EntityNotFoundException;
    void deleteSpeciality(Long id) throws EntityNotFoundException;
    List<Speciality> getSpecialityByName(String specialty) throws EntityNotFoundException;
    Speciality getSpecialityById(Long id) throws EntityNotFoundException;
}
