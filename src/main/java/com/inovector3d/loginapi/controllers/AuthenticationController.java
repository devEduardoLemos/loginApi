package com.inovector3d.loginapi.controllers;

import com.inovector3d.loginapi.dto.AuthenticationDTO;
import com.inovector3d.loginapi.dto.LoginResponseDTO;
import com.inovector3d.loginapi.entities.User;
import com.inovector3d.loginapi.service.AuthenticationService;
import com.inovector3d.loginapi.service.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new AuthenticationException("User or password invalid.");
        }
    }

}
