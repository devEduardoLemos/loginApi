package com.inovector3d.loginapi.dto;

import com.inovector3d.loginapi.entities.User;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LoginResponseDTO extends UserDTO{
    private String token;
    private Instant expiresAt;

    public LoginResponseDTO(){

    }

    public LoginResponseDTO(String token, Instant expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public LoginResponseDTO(Long id, String firstName, String lastName, String email, String token, Instant expiresAt) {
        super(id, firstName, lastName, email);
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public LoginResponseDTO(User entity, String token, Instant expiresAt) {
        super(entity);
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime zonedDateTime = expiresAt.atZone(ZoneId.of("GMT-3"));
        return zonedDateTime.format(formatter);
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
