package com.skip.loginapi.service.validation;

import com.skip.loginapi.controllers.exceptions.FieldMessage;
import com.skip.loginapi.dto.UserUpdateDTO;
import com.skip.loginapi.entities.User;
import com.skip.loginapi.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        System.out.println(userId);

        List<FieldMessage> list = new ArrayList<>();

        User user = userRepository.findByEmail(dto.getEmail());
        if(user!= null && userId != user.getId()){
            list.add(new FieldMessage("email", "Email already exists"));
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
