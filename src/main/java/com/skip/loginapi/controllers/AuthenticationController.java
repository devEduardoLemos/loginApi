package com.skip.loginapi.controllers;

import com.skip.loginapi.dto.AuthenticationDTO;
import com.skip.loginapi.dto.LoginResponseDTO;
import com.skip.loginapi.entities.User;
import com.skip.loginapi.service.AuthenticationService;
import com.skip.loginapi.service.exceptions.UserAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value="/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
        try {
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = authenticationService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok().body(token);
        }catch(Exception exception){
            throw new UserAuthenticationException("User or password invalid.");
        }
    }

}
