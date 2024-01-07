package com.yyc.bunnyroom.mypage.user.model;

import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.mypage.user.dto.ReservationListDTO;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuestMapper {

    LoginUserDTO selectByUserEmail(String userEmail);


    int updateUserPassword(LoginUserDTO loginUserDTO);

    int withdrawByUserNo(@Param("userNo") int userNo, @Param("reason")String reason, @Param("update") String update);

    int changeNicknameByUserNo(@Param("userNo") int userNo, @Param("nickName") String nickName);

    int changePhoneByUserNo(@Param("userNo") int userNo, @Param("phone") String phone);

    int changePasswordByUserNo(@Param("userNo") int userNo, @Param("encodedNewPassword") String password);

    List<ReservationListDTO> showReservation(int userNo);

    int cancelReservation(@Param("reservationNo") int reservationNo, @Param("reason") String reason, @Param("cancelDate") String cancelDate);

    int cancelReservationBecauseOfWithdraw(@Param("userNo") int userNo, @Param("update") String update);

    List<ReservationListDTO> showReservationByRoomNo(int roomNo);
}
