package com.yyc.bunnyroom.signup.service;

import com.yyc.bunnyroom.signup.dao.UserMapper;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.signup.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginUserDTO findByUserEmail(String userEmail){

        LoginUserDTO loginUser = userMapper.findByUserEmail(userEmail);
        if (!Objects.isNull(loginUser)){
            return loginUser;
        }else {
            return null;
        }
    }

    @Transactional
    public int registUser(SignupDTO signupDTO){

        signupDTO.setUserPassword(passwordEncoder.encode(signupDTO.getUserPassword()));
        int result = userMapper.registUser(signupDTO);

        return result;
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error:
             errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }
}