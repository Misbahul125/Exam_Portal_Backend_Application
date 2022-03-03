package com.examserver.services.implementations;

import com.examserver.models.UserModel;
import com.examserver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = this.userRepository.findByUserName(username);

        if (userModel != null) {
            return userModel;
        }
        else {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found!");
        }
    }
}
