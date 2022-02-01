package com.examserver.services.implementations;

import com.examserver.models.UserModel;
import com.examserver.models.UserRoleModel;
import com.examserver.repos.RoleRepository;
import com.examserver.repos.UserRepository;
import com.examserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @Override
    public UserModel createUser(UserModel userModel, Set<UserRoleModel> userRoleModels) throws Exception {

        UserModel local = this.userRepository.findByUserName(userModel.getUserName());

        //check if user already exist
        if (local != null) {
            System.out.println("User exists!!");
            throw new Exception("User already exists! Please try to login.");
        }
        else {
            //create the new user as user doesn't exist

            for (UserRoleModel userRoleModel : userRoleModels) {
                roleRepository.save(userRoleModel.getRole());
            }

            //assign all roles to user
            userModel.getUserRoles().addAll(userRoleModels);
            local = this.userRepository.save(userModel);
        }

        return local;

    }

    //fetching user details
    @Override
    public UserModel getUserByUsername(String username) {

        return this.userRepository.findByUserName(username);

    }

}
