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


    public int updateUserPassword(LoginUserDTO loginUserDTO){
        return guestMapper.updateUserPassword(loginUserDTO);
    }

    /**
     * 게스트 탈퇴를 수행하는 메소드
     * */
    public int withdrawByUserNo(int userNo, String reason, String update) {

        int result = guestMapper.withdrawByUserNo(userNo, reason, update);

        if(result > 0){
            return result;
        }else {
            return 0;
        }
    }

    /**
     * 게스트의 닉네임을 수정하는 메소드
     * */
    public boolean changeNicknameByUserNo(int userNo, String nickName) {

        int result = guestMapper.changeNicknameByUserNo(userNo, nickName);

        if(result > 0){
            return true;
        }else {
            return false;
        }
    }
}
