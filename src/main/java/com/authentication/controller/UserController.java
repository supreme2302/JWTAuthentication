package com.authentication.controller;

import com.authentication.model.MainUser;
import com.authentication.model.Message;
import com.authentication.model.Task;
import com.authentication.model.UserDto;
import com.authentication.service.TaskService;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private enum UserStatus {
        SUCCESSFULLY_REGISTERED,
        SUCCESSFULLY_AUTHED,
        SUCCESSFULLY_LOGGED_OUT,
        ACCESS_ERROR,
        WRONG_CREDENTIALS,
        NOT_UNIQUE_USERNAME,
        ALREADY_AUTHENTICATED,
        NOT_FOUND,
        SUCCESSFULLY_ADDED
    }

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<MainUser> listUser() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public MainUser getOne(@PathVariable(value = "id") Long id) {
        return userService.findById(id);
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public MainUser saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/notes/{username}")
    public ResponseEntity getProfileUser(@PathVariable("username") String username) {

        MainUser user = userService.getUserByName(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(UserStatus.NOT_FOUND));
        }

        List<Task> taskList = taskService.getTasksByAuthor(username);
        return ResponseEntity.ok(taskList);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ResponseEntity addNote(@RequestBody Task task) {
        taskService.addNote(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Message(UserStatus.SUCCESSFULLY_ADDED));
    }
}
