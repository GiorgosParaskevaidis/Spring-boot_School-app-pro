package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolpro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.repository.StudentRepository;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class IStudentServiceImpl implements IStudentService{

    private StudentRepository studentRepository;

    @Transactional
    @Override
    public Student insertStudent(StudentInsertDTO studentInsertDTO) throws Exception {
        Student student = null;

        try {
            student = studentRepository.save(Mapper.mapToStudent(studentInsertDTO));
            if (student.getId() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert Success for student with id:" + student.getId());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return student;
    }

    @Transactional
    @Override
    public Student updateStudent(StudentUpdateDTO studentUpdateDTO) throws EntityNotFoundException {
        Student studentToUpdate = null;
        Student updatedStudent = null;

        try {
            studentToUpdate = studentRepository.findStudentById(studentUpdateDTO.getId());
            if (studentToUpdate == null) {
                throw new EntityNotFoundException(Student.class, studentUpdateDTO.getId());
            }
            updatedStudent = studentRepository.save(Mapper.mapToStudent(studentUpdateDTO));
            log.info("Student with id:" + updatedStudent.getId() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedStudent;
    }

    @Transactional
    @Override
    public void deleteStudent(Long id) throws EntityNotFoundException {
        Student student = null;
        try {
            student = studentRepository.findStudentById(id);
            if (student == null) {
                throw new EntityNotFoundException(Student.class, id);
            }
            studentRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException {
        List<Student> students = new ArrayList<>();

        try {
           students = studentRepository.findByLastnameStartingWith(lastname);
           if (students.isEmpty()) throw new EntityNotFoundException(Student.class, 0L);
           log.info("Students with lastname starting with: " + lastname + "were not found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return students;
    }

    @Transactional
    @Override
    public Student getStudentById(Long id) throws EntityNotFoundException {
        Student student = null;

        try {
            student = studentRepository.findStudentById(id);
            if (student == null) throw new EntityNotFoundException(Student.class, id);
            log.info("Student with id " + id + "was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return student;
    }
}
