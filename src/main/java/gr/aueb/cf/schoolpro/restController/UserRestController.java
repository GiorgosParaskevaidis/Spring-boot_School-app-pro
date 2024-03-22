package gr.aueb.cf.schoolpro.restController;

import gr.aueb.cf.schoolpro.dto.*;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.service.ITeacherService;
import gr.aueb.cf.schoolpro.service.IUserService;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import gr.aueb.cf.schoolpro.validator.TeacherInsertValidator;
import gr.aueb.cf.schoolpro.validator.TeacherUpdateValidator;
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
public class UserRestController {

    private final IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserReadOnlyDTO>> getUsersByUsername(@RequestParam("username") String username) {
        List<User> users;

        try {
            users = userService.getUsersByUsername(username);
            List<UserReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (User user : users) {
                readOnlyDTOS.add(Mapper.mapToUserReadOnlyDTO(user));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            //throw e;
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserReadOnlyDTO> getUser(Long id) {
        User user;

        try {
            user = userService.getUserById(id);
            UserReadOnlyDTO dto = Mapper.mapToUserReadOnlyDTO(user);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teachers")
    public ResponseEntity<UserReadOnlyDTO> addUser(@Valid @RequestBody UserInsertDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.insertUser(dto);
            UserReadOnlyDTO userReadOnlyDTO = Mapper.mapToUserReadOnlyDTO(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userReadOnlyDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).body(userReadOnlyDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserReadOnlyDTO> updateUser(@PathVariable("id") Long id,@Valid @RequestBody UserUpdateDTO dto, BindingResult bindingResult ) {

        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.updateUser(dto);
            UserReadOnlyDTO readOnlyDTO = Mapper.mapToUserReadOnlyDTO(user);
            return new ResponseEntity<>(readOnlyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserReadOnlyDTO> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
