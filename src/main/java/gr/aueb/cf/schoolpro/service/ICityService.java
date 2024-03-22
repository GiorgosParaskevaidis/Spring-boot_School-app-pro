package gr.aueb.cf.schoolpro.service;



import gr.aueb.cf.schoolpro.dto.CityInsertDTO;
import gr.aueb.cf.schoolpro.dto.CityUpdateDTO;
import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICityService {
    City insertCity(CityInsertDTO cityInsertDTO) throws Exception;
    City updateCity(CityUpdateDTO cityUpdateDTO) throws EntityNotFoundException;
    void deleteCity(Long id) throws EntityNotFoundException;
    List<City> getCityByCityname(String cityName) throws EntityNotFoundException;
    City getCityById(Long id) throws EntityNotFoundException;
}
