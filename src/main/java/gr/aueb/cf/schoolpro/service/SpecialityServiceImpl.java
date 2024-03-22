package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolpro.dto.SpecialityUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.model.Speciality;
import gr.aueb.cf.schoolpro.repository.SpecialityRepository;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialityServiceImpl implements ISpecialityService{

    private SpecialityRepository specialityRepository;

    @Transactional
    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO specialityInsertDTO) throws Exception {
        Speciality speciality = null;

        try {
            speciality = specialityRepository.save(Mapper.mapToSpeciality(specialityInsertDTO));
            if (speciality.getId() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert Success for speciality with id:" + speciality.getId());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return speciality;
    }

    @Transactional
    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO specialityUpdateDTO) throws EntityNotFoundException {
        Speciality speciality = null;
        Speciality updatedSpeciality;

        try {
            speciality = specialityRepository.findSpecialityById(specialityUpdateDTO.getId());
            if (speciality == null) {
                throw new EntityNotFoundException(City.class, specialityUpdateDTO.getId());
            }
            updatedSpeciality = specialityRepository.save(Mapper.mapToSpeciality(specialityUpdateDTO));
            log.info("Speciality with id:" + updatedSpeciality.getId() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedSpeciality;
    }

    @Transactional
    @Override
    public void deleteSpeciality(Long id) throws EntityNotFoundException {
        Speciality speciality = null;

        try {
            speciality = specialityRepository.findSpecialityById(id);
            if (speciality == null) {
                throw new EntityNotFoundException(Speciality.class, id);
            }
            specialityRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public List<Speciality> getSpecialityByName(String specialty) throws EntityNotFoundException {
        List<Speciality> specialities = new ArrayList<>();

        try {
            specialities = specialityRepository.findBySpecialityStartingWith(specialty);
            if (specialities.isEmpty()) throw new EntityNotFoundException(Speciality.class, 0L);
            log.info("Specialities with name starting with: " + specialty + "were not found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return specialities;
    }

    @Override
    public Speciality getSpecialityById(Long id) throws EntityNotFoundException {
        Speciality speciality;

        try {
            speciality = specialityRepository.findSpecialityById(id);
            if (speciality == null) throw new EntityNotFoundException(Speciality.class, id);
            log.info("Speciality with id " + id + "was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return speciality;
    }
}
