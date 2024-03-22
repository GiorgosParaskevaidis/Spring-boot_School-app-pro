package gr.aueb.cf.schoolpro.repository;

import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastnameStartingWith(String lastname);
    Student findStudentById(Long id);
}
