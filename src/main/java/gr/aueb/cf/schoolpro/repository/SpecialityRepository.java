package gr.aueb.cf.schoolpro.repository;

import gr.aueb.cf.schoolpro.model.Speciality;
import gr.aueb.cf.schoolpro.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    List<Speciality> findBySpecialityStartingWith(String speciality);
    Speciality findSpecialityById(Long id);
}
