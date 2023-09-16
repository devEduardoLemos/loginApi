package com.inovector3d.loginapi.dto;

import java.io.Serializable;

public class AuthenticationDTO implements Serializable {

    private String email;
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
