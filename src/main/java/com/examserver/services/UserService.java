package com.examserver.services;

import com.examserver.models.UserModel;
import com.examserver.models.UserRoleModel;

import java.util.Set;

public interface UserService {

    //create user
    public UserModel createUser(UserModel userModel , Set<UserRoleModel> userRoleModels) throws Exception;

    //fetch user details by username
    public UserModel getUserByUsername(String username);

}
