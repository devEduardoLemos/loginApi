package com.skip.loginapi.service.validation;

import com.skip.loginapi.controllers.exceptions.FieldMessage;
import com.skip.loginapi.dto.UserInsertDTO;
import com.skip.loginapi.entities.User;
import com.skip.loginapi.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(dto.getEmail());
        if(user != null){
            list.add(new FieldMessage("email","Email already exists"));
        }

        for(FieldMessage message : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message.getMessage())
                    .addPropertyNode(message.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
