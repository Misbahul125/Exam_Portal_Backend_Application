package com.examserver.controllers;

import com.examserver.models.RoleModel;
import com.examserver.models.UserModel;
import com.examserver.models.UserRoleModel;
import com.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //function to create user
    @PostMapping("/")
    public UserModel createUser(@RequestBody UserModel userModel) throws Exception {

        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(50L);
        roleModel.setRoleName("NORMAL");

        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUser(userModel);
        userRoleModel.setRole(roleModel);

        Set<UserRoleModel> userRoleModelSet = new HashSet<>();
        userRoleModelSet.add(userRoleModel);

        return this.userService.createUser(userModel , userRoleModelSet);

    }

    //function to fetch user details by username
    @GetMapping("/{username}")
    public UserModel getUserByUsername(@PathVariable("username") String username) {

        return this.userService.getUserByUsername(username);

    }

    //function to delete user by userId
    @DeleteMapping("/{userID}")
    public void deleteUserByUserId(@PathVariable("userID") Long userId) {
        this.userService.deleteUserByUserId(userId);
    }

}
