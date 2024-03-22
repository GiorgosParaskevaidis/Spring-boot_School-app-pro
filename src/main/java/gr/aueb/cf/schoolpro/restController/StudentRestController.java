package gr.aueb.cf.schoolpro.restController;

import gr.aueb.cf.schoolpro.dto.*;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Student;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.service.IStudentService;
import gr.aueb.cf.schoolpro.service.IUserService;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentRestController {

    private final IStudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentReadOnlyDTO>> getStudentsByLastname(@RequestParam("lastname") String lastname) {
        List<Student> students;

        try {
            students = studentService.getStudentsByLastname(lastname);
            List<StudentReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Student student : students) {
                readOnlyDTOS.add(Mapper.mapToStudentReadOnlyDTO(student));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //throw e;
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentReadOnlyDTO> getStudent(Long id) {
        Student student;

        try {
            student = studentService.getStudentById(id);
            StudentReadOnlyDTO dto = Mapper.mapToStudentReadOnlyDTO(student);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teachers")
    public ResponseEntity<StudentReadOnlyDTO> addUser(@Valid @RequestBody StudentInsertDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Student student = studentService.insertStudent(dto);
            StudentReadOnlyDTO studentReadOnlyDTO = Mapper.mapToStudentReadOnlyDTO(student);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(studentReadOnlyDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).body(studentReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<StudentReadOnlyDTO> updateStudent(@PathVariable("id") Long id,@Valid @RequestBody StudentUpdateDTO dto, BindingResult bindingResult ) {

        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Student student = studentService.updateStudent(dto);
            StudentReadOnlyDTO readOnlyDTO = Mapper.mapToStudentReadOnlyDTO(student);
            return new ResponseEntity<>(readOnlyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentReadOnlyDTO> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
