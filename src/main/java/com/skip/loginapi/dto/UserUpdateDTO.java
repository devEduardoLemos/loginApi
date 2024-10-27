package com.skip.loginapi.dto;

import com.skip.loginapi.service.validation.UserUpdateValid;
import jakarta.validation.constraints.Pattern;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO{

    // Optional password field for updates
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$",
            message = "Password must have at least one number, one special character, one lowercase letter, one uppercase letter, and a minimum length of six characters."
    )
    private String password;

    public UserUpdateDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
