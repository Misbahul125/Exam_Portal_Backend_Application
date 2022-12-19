package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @PostMapping("/")
    public User createUser(
            @RequestBody User user
    ) throws Exception {

        user.setProfileImage("default.png");

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> userRoles = new HashSet<>();

        Role role = new Role(45L, "NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoles.add(userRole);

        return this.userService.createUser(user, userRoles);

    }

    @GetMapping("/{username}")
    public User getUser(
            @PathVariable("username") String username
    ) {
        return this.userService.getUser(username);
    }

    //delete the user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable("userId") Long userId
    ) {
        this.userService.deleteUser(userId);
    }

    //update api


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

}
