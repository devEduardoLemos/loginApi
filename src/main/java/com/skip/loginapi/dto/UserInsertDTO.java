package com.skip.loginapi.dto;

import com.skip.loginapi.service.validation.UserInsertValid;
import jakarta.validation.constraints.Pattern;

@UserInsertValid
public class UserInsertDTO extends UserDTO{

    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$",
            message = "Password must have at least one number, one special character, one lowercase letter, one uppercase letter, and a minimum length of six characters."
    )
    private String password;

    UserInsertDTO(){
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
