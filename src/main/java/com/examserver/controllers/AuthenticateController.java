package com.examserver.controllers;

import com.examserver.config.JwtUtil;
import com.examserver.models.JWTRequest;
import com.examserver.models.JWTResponse;
import com.examserver.services.implementations.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Autowired
    private JwtUtil jwtUtil;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername() , jwtRequest.getPassword());
        }
        catch (UsernameNotFoundException usernameNotFoundException) {
            usernameNotFoundException.printStackTrace();
            throw new Exception("Username not found ");
        }

        //user authenticated

        UserDetails userDetails = this.userDetailsServiceImplementation.loadUserByUsername(jwtRequest.getUsername());

        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(String username , String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username , password));
        }
        catch (DisabledException disabledException) {
            throw  new Exception("User Disabled  "+disabledException.getMessage());
        }
        catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Invalid Credentials  "+badCredentialsException.getMessage());
        }
    }
}
