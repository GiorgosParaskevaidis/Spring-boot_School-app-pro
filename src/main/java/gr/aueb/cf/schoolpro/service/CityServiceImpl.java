package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.CityInsertDTO;
import gr.aueb.cf.schoolpro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.repository.CityRepository;
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
public class CityServiceImpl implements ICityService{

    private CityRepository cityRepository;

    @Transactional
    @Override
    public City insertCity(CityInsertDTO cityInsertDTO) throws Exception {
        City city = null;

        try {
            city = cityRepository.save(Mapper.mapToCity(cityInsertDTO));
            if (city.getId() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert Success for city with id:" + city.getId());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return city;
    }

    @Transactional
    @Override
    public City updateCity(CityUpdateDTO cityUpdateDTO) throws EntityNotFoundException {
        City city = null;
        City updatedCity;

        try {
            city = cityRepository.findCityById(cityUpdateDTO.getId());
            if (city == null) {
                throw new EntityNotFoundException(City.class, cityUpdateDTO.getId());
            }
            updatedCity = cityRepository.save(Mapper.mapToCity(cityUpdateDTO));
            log.info("City with id:" + updatedCity.getId() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedCity;
    }

    @Transactional
    @Override
    public void deleteCity(Long id) throws EntityNotFoundException {
        City city = null;

        try {
            city = cityRepository.findCityById(id);
            if (city == null) {
                throw new EntityNotFoundException(City.class, id);
            }
            cityRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public List<City> getCityByCityname(String cityName) throws EntityNotFoundException {
        List<City> cities = new ArrayList<>();

        try {
            cities = cityRepository.findByCityNameStartingWith(cityName);
            if (cities.isEmpty()) throw new EntityNotFoundException(City.class, 0L);
            log.info("Cities with name starting with: " + cityName + "were not found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return cities;
    }

    @Override
    public City getCityById(Long id) throws EntityNotFoundException {
        City city;

        try {
            city = cityRepository.findCityById(id);
            if (city == null) throw new EntityNotFoundException(City.class, id);
            log.info("City with id " + id + "was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return city;
    }
}
