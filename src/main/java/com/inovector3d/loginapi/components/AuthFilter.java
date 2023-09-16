package com.inovector3d.loginapi.components;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.inovector3d.loginapi.entities.User;
import com.inovector3d.loginapi.repositories.UserRepository;
import com.inovector3d.loginapi.service.AuthenticationService;
import com.inovector3d.loginapi.service.exceptions.UserAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token !=null){
            try {
                var userInfo = authenticationService.validateToken(token);
                var auxUserInfo = userInfo.split(","); //[0]id, [1]first name, [2]last name, [3]email, [4]authorities
                UserDetails user = userRepository.findByEmail(auxUserInfo[3]);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }catch(JWTVerificationException exception){
                throw new UserAuthenticationException(exception.getMessage());
            }
        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ","");
    }
}
