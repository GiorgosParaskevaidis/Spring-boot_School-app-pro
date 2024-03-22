package gr.aueb.cf.schoolpro.service;


import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IStudentService {

    Student insertStudent(StudentInsertDTO studentInsertDTO) throws Exception;
    Student updateStudent(StudentUpdateDTO studentUpdateDTO) throws EntityNotFoundException;
    void deleteStudent(Long id) throws EntityNotFoundException;
    List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException;
    Student getStudentById(Long id) throws EntityNotFoundException;
}
