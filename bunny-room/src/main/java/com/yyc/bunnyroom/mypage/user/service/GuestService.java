package com.yyc.bunnyroom.mypage.user.service;

import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.mypage.user.model.GuestMapper;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class GuestService {

    private GuestMapper guestMapper;

    public GuestService(GuestMapper userMyPageMapper) {
        this.guestMapper = userMyPageMapper;
    }





    public LoginUserDTO selectByUserEmail(String userEmail){
        LoginUserDTO user = guestMapper.selectByUserEmail(userEmail);


        if(Objects.isNull(user)){
            throw new NullPointerException();
        }else {
            return user;
        }
    }


//    public int updateUserPassword(LoginUserDTO loginUserDTO){
//        return guestMapper.updateUserPassword(loginUserDTO);
//    }
//
//    /**
//     * 게스트 탈퇴를 수행하는 메소드
//     * */
//    public int withdrawByUserNo(int userNo) {
//
//        int result = guestMapper.withdrawByUserNo(userNo);
//    }
}
