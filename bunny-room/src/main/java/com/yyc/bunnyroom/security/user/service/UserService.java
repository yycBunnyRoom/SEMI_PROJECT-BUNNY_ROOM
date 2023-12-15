package com.yyc.bunnyroom.security.user.service;

import com.yyc.bunnyroom.security.user.dao.UserMapper;
import com.yyc.bunnyroom.security.user.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.security.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
