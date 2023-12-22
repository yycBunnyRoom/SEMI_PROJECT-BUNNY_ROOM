package com.yyc.bunnyroom.mypage.user.model;

import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GuestMapper {

    UserDTO findByUserEmail(String userEmail);

    int updateUserPassword(LoginUserDTO loginUserDTO);
}
