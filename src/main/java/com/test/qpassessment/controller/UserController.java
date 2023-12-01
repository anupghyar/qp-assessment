package com.test.qpassessment.controller;

import com.test.qpassessment.model.User;
import com.test.qpassessment.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("admin/v1/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users with a particular username or email.
     *
     * @param username/email
     * @return all users or users with a particular username or email in the system
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUsers(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "email", required = false) String email) {

        Collection<User> users = new ArrayList<User>();
        if (username != null) {
            User user = userService.findByUsername(username);
            users.add(user);
        } else if (email != null) {
            User user = userService.findByEmail(email);
            users.add(user);
        } else {
            Collection<User> allUser = userService.findAll();
            users.addAll(allUser);
        }
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    /**
     * Create new user
     *
     * @param user
     * @return the created user and HttpStatus.CREATED if user was successfully created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws BadRequestException {

        user = userService.create(user);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<User>(user, HttpStatus.CREATED);

    }

}