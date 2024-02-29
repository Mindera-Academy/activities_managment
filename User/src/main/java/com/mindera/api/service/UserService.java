package com.mindera.api.service;

import com.mindera.api.domain.User;
import com.mindera.api.exception.DocumentIsRequiredException;
import com.mindera.api.exception.GenderNotFoundException;
import com.mindera.api.exception.UserAlreadyExistsException;
import com.mindera.api.exception.UserNotFoundException;
import com.mindera.api.model.Gender;
import com.mindera.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    Map<Integer, User> databaseUser = new HashMap<>();

    public User getOneUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        validateUserNotFound(user, id, "not found");
        return user.get();
    }

    public List<User> getAllUsers() {

            return userRepository.findAll();
        }


    public User addOneUser(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("duplicate key")) {
                throw new UserAlreadyExistsException("The user already exists!");
            }
            throw new DocumentIsRequiredException("Document is required!", e);
        }
        return user;
    }

    public void updateUser(Integer id, User user) {
        validateUserNotFound(userRepository.findById(id), id, " not found!");
        user.setId(id);
        userRepository.save(user);
    }

    public void removeUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        validateUserNotFound(user, id, " not found!");
        userRepository.delete(user.get());
    }

    private void validateUserNotFound(Optional<User> userRepository, Integer id, String x) {
        if (userRepository.isEmpty()) {
            throw new UserNotFoundException("User " + id + x);
        }
    }
}
