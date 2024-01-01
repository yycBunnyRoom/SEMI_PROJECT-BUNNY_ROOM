package com.yyc.bunnyroom.reservation.controller;


import com.yyc.bunnyroom.reservation.model.ReservationDTO;
import com.yyc.bunnyroom.reservation.service.ReservationService;
import com.yyc.bunnyroom.roomRegister.model.BusinessDTO;
import com.yyc.bunnyroom.roomRegister.model.TimeScheduleDTO;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    /* 예약 등록 */
    @PostMapping("/addReservation")
    public ResponseEntity<Integer> addReservation(@RequestBody ReservationDTO newReservation){

        System.out.println("reservation컨트롤러로 들어온 정보: "+newReservation);

        /* 예약일을 제대로된 형식으로 입력 */
        Instant instant = newReservation.getDate().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        newReservation.setReservationDate(instant.atZone(zoneId));

        /* 회원번호를 입력 */
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userNo;
        userNo = ((AuthDetails)currentUser).getLoginUserDTO().getUserNo();

        newReservation.setUserNo(userNo);

        /* 예약상태를 입력 (예약중) */
        newReservation.setReservationStatus("예약중");

        /* 등록날짜 입력 */
        ZonedDateTime currentTime = ZonedDateTime.now();
        newReservation.setReservationRegistDate(currentTime);


        System.out.println("등록되는 폼이 제대로 된지 확인: "+newReservation);


        reservationService.addReservation(newReservation);

        Integer result = newReservation.getReservationIdx();

        System.out.println("예약 번호: "+result);

        if (result > 0) {
            // 성공적으로 등록됨을 나타내는 1 반환
            return ResponseEntity.ok(1);
        } else {
            // 등록 실패를 나타내는 0 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }


}
