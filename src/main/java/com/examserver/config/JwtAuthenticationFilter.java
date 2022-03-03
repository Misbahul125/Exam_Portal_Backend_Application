package com.examserver.config;

import com.examserver.services.implementations.UserDetailsServiceImplementation;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);
            System.out.println("Jwt token is  "+jwtToken );

            try {
                username = this.jwtUtil.extractUsername(jwtToken);
                System.out.println("The username is "+username);
            }
            catch (ExpiredJwtException expiredJwtException) {
                expiredJwtException.printStackTrace();
                System.out.println("JWT Token has expired");
            }
            catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("Error!   "+exception.getMessage());
            }

        }
        else {

            System.out.println("Invalid Token : Not start with bearer");
        }

        //validate token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.userDetailsServiceImplementation.loadUserByUsername(username);

            if (this.jwtUtil.validateToken(jwtToken , userDetails)) {

                //token is valid

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                        (userDetails , null , userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        else {
            System.out.println("Token is not valid");
        }

        filterChain.doFilter(request , response);
    }
}
