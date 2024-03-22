package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolpro.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.repository.TeacherRepository;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService{

    private TeacherRepository teacherRepository;

    @Transactional
    @Override
    public Teacher insertTeacher(TeacherInsertDTO teacherInsertDTO) throws Exception {
        Teacher teacher = null;

        try {
            teacher = teacherRepository.save(Mapper.mapToTeacher(teacherInsertDTO));
            if (teacher.getId() == null) {
                throw new Exception("Insert Error");
            }
            // if entity already exists DO NOT insert again
            log.info("Insert Success for teacher with id:" + teacher.getId());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return teacher;
    }

    @Transactional
    @Override
    public Teacher updateTeacher(TeacherUpdateDTO teacherUpdateDTO) throws EntityNotFoundException {
        Teacher teacher = null;
        Teacher updatedTeacher;

        try {
            teacher = teacherRepository.findTeacherById(teacherUpdateDTO.getId());
            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, teacherUpdateDTO.getId());
            }
            updatedTeacher = teacherRepository.save(Mapper.mapToTeacher(teacherUpdateDTO));
            log.info("Teacher with id:" + updatedTeacher.getId() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedTeacher;
    }

    @Transactional
    @Override
    public void deleteTeacher(Long id) throws EntityNotFoundException {
        Teacher teacher = null;

        try {
            teacher = teacherRepository.findTeacherById(id);
            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }
            teacherRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers = new ArrayList<>();

        try {
            teachers = teacherRepository.findByLastnameStartingWith(lastname);
            if (teachers.isEmpty()) throw new EntityNotFoundException(Teacher.class, 0L);
            log.info("Teachers with lastname starting with: " + lastname + "were not found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return teachers;
    }

    @Transactional
    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Teacher teacher;

        try {
            teacher = teacherRepository.findTeacherById(id);
            if (teacher == null) throw new EntityNotFoundException(Teacher.class, id);
            log.info("Teacher with id " + id + "was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return teacher;
    }
}
