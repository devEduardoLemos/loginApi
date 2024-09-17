package com.skip.loginapi.controllers;

import com.skip.loginapi.dto.AuthenticationDTO;
import com.skip.loginapi.dto.LoginResponseDTO;
import com.skip.loginapi.dto.RefreshTokenDTO;
import com.skip.loginapi.entities.RefreshToken;
import com.skip.loginapi.entities.User;
import com.skip.loginapi.service.AuthenticationService;
import com.skip.loginapi.service.RefreshTokenService;
import com.skip.loginapi.service.exceptions.UserAuthenticationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO dto) {
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new UserAuthenticationException("Password cannot be empty or null.");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        try {
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = authenticationService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok().body(token);
        } catch (Exception exception) {
            throw new UserAuthenticationException("User or password invalid.");
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenDTO.TokenRefreshRequest request){
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = authenticationService.generateToken((User) user).getToken();
                    return ResponseEntity.ok(new RefreshTokenDTO.TokenRefreshResponse(token, requestRefreshToken));

                }).orElseThrow(()-> new RuntimeException("Refresh token is not in database!"));
    }
}
