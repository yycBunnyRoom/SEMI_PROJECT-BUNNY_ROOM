package com.yyc.bunnyroom.mypage.user.model;

import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuestMapper {

    LoginUserDTO selectByUserEmail(String userEmail);


    int updateUserPassword(LoginUserDTO loginUserDTO);

    int withdrawByUserNo(@Param("userNo") int userNo, @Param("reason")String reason, @Param("update") String update);
}
