package gr.aueb.cf.schoolpro.service;

import gr.aueb.cf.schoolpro.dto.UserInsertDTO;
import gr.aueb.cf.schoolpro.dto.UserUpdateDTO;
import gr.aueb.cf.schoolpro.mapper.Mapper;
import gr.aueb.cf.schoolpro.model.Teacher;
import gr.aueb.cf.schoolpro.model.User;
import gr.aueb.cf.schoolpro.repository.UserRepository;
import gr.aueb.cf.schoolpro.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Transactional
    @Override
    public User insertUser(UserInsertDTO userInsertDTO) throws Exception {
        User user = null;


        try {
            user = userRepository.save(Mapper.mapToUser(userInsertDTO));
            if (user.getId() == null) {
                throw new Exception("Insert Error");
            }
            log.info("Insert Success for user with id:" + user.getId());
        } catch (Exception e) {
            log.error("insert error" + e.getMessage());
            throw e;
        }
        return user;
    }

    @Transactional
    @Override
    public User updateUser(UserUpdateDTO userUpdateDTO) throws EntityNotFoundException {
        User user = null;
        User updatedUser = null;

        try {
            user = userRepository.findUserById(userUpdateDTO.getId());
            if (user == null) {
                throw new EntityNotFoundException(User.class, userUpdateDTO.getId());
            }
            updatedUser = userRepository.save(Mapper.mapToUser(userUpdateDTO));
            log.info("User with id:" + updatedUser.getId() + "was updated");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return updatedUser;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        User user = null;

        try {
            user = userRepository.findUserById(id);
            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public List<User> getUsersByUsername(String username) throws EntityNotFoundException {
        List<User> users;

        try {
            users = userRepository.findByUsernameStartingWith(username);
            if (users.isEmpty()) throw new EntityNotFoundException(User.class, 0L);
            log.info("Users with username starting with: " + username + "were not found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Transactional
    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user = null;

        try {
            user = userRepository.findUserById(id);
            if (user == null) throw new EntityNotFoundException(Teacher.class, id);
            log.info("User with id " + id + "was found");
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return user;
    }
}
