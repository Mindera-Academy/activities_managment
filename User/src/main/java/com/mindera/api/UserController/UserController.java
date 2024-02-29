package com.mindera.api.UserController;

import com.mindera.api.domain.User;
import com.mindera.api.exception.DocumentIsRequiredException;
import com.mindera.api.exception.GenderNotFoundException;
import com.mindera.api.exception.UserAlreadyExistsException;
import com.mindera.api.exception.UserNotFoundException;
import com.mindera.api.model.Error;
import com.mindera.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")


public class UserController {
    private UserService UserService;

    public UserController(UserService service) {
        this.UserService = UserService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = UserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = UserService.getOneUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UserService.addOneUser(user);
        return ResponseEntity.ok(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User currentUser = UserService.getOneUser(id);
        if (currentUser != null) {
            currentUser.setName(user.getName());
            // Update other fields as needed
            UserService.updateUser(currentUser);
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable("id") int id) {
        UserService.removeUser(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleUserNotFound(UserNotFoundException ex){
        Error error = new Error();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleUserConflict(UserAlreadyExistsException ex){
        Error error = new Error();
        error.setErrorCode(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler({DocumentIsRequiredException.class, GenderNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Error> handleUserPreConditionFailed(RuntimeException ex){
        Error error = new Error();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(error);
    }
}

