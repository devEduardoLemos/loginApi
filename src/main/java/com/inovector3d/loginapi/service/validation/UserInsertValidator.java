package com.inovector3d.loginapi.service.validation;

import com.inovector3d.loginapi.controllers.exceptions.FieldMessage;
import com.inovector3d.loginapi.dto.UserInsertDTO;
import com.inovector3d.loginapi.entities.User;
import com.inovector3d.loginapi.repositories.UserRepository;
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
