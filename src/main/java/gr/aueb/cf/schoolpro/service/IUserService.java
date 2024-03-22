package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {

    User insertUser(UserInsertDTO userInsertDTO) throws Exception;
    User updateUser(UserUpdateDTO userUpdateDTO) throws EntityNotFoundException;
    void deleteUser(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
}
