package com.skip.loginapi.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class AuthenticationDTO implements Serializable {

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public AuthenticationDTO(){

    }

    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
