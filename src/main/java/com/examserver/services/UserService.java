package com.examserver.services;

import com.examserver.models.UserModel;
import com.examserver.models.UserRoleModel;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Set;

public interface UserService {

    //create user
    public UserModel createUser(UserModel userModel , Set<UserRoleModel> userRoleModels) throws Exception;

    //fetch user details by username
    public UserModel getUserByUsername(String username);

    //update user by userId
    @Modifying
    public UserModel updateUser(UserModel userModel);

    //delete user by userId
    public void deleteUserByUserId(Long userId);

}
