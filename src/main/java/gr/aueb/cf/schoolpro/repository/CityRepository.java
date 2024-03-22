package gr.aueb.cf.schoolpro.repository;

import gr.aueb.cf.schoolpro.model.City;
import gr.aueb.cf.schoolpro.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCityNameStartingWith(String cityName);
    City findCityById(Long id);
}
