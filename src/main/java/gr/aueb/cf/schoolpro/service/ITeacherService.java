package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface ITeacherService {

    Teacher insertTeacher(TeacherInsertDTO teacherInsertDTO) throws Exception;
    Teacher updateTeacher(TeacherUpdateDTO teacherUpdateDTO) throws EntityNotFoundException;
    void deleteTeacher(Long id) throws EntityNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException;
    Teacher getTeacherById(Long id) throws EntityNotFoundException;
}
