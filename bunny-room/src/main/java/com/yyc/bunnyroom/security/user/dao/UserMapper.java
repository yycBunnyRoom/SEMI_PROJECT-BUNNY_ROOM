package com.yyc.bunnyroom.security.user.dao;

import com.yyc.bunnyroom.security.user.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.security.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int registUser(SignupDTO signupDTO);

    LoginUserDTO findByUserEmail(String userEmail);
}
