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



    public List<LoginUserDTO> findByAllUserEmail(){
        List<LoginUserDTO> mypage= guestMapper.findByAllUserEmail();

        if(Objects.isNull(mypage)){
            System.out.println("오류");
        }
        return mypage;
    }


//    public UserDTO findByUserEmail(String userEmail){
//        UserDTO user = guestMapper.findByUserEmail(userEmail);
//
//
//
//        if(Objects.isNull(user)){
//            throw new NullPointerException();
//        }else {
//            return user;
//        }
//    }

//    @Transactional
//    public boolean changePassword(String userEmail, String currentPassword, String newPassword, String confirmPassword) {
//        // 사용자 정보 가져오기
//        UserDTO user = guestMapper.findByUserEmail(userEmail);
//
//        if (user == null) {
//            return false; // 사용자가 존재하지 않으면 변경 실패
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            return false; // 새 비밀번호와 확인 비밀번호가 일치하지 않으면 변경 실패
//        }
//
//
//        // 새로운 비밀번호를 사용자 정보에 업데이트
//        guestMapper.updatePassword(userEmail, currentPassword, newPassword);
//
//        return true; // 비밀번호 변경 성공
//    }

    public int updateUserPassword(LoginUserDTO loginUserDTO){
        return guestMapper.updateUserPassword(loginUserDTO);
    }

}
