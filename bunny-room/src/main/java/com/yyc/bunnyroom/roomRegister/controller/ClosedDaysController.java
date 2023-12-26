package com.yyc.bunnyroom.roomRegister.controller;


import com.yyc.bunnyroom.common.eNum.WeekDay;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.SelectedDaysDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("roomRegister/closedDays")
public class ClosedDaysController {
    @Autowired
    RoomRegisterService roomRegisterService;

    @PostMapping("/register")
    public ResponseEntity<String> saveSelectedDays(@RequestBody SelectedDaysDTO requestData) {
        List<String> selectedDays = requestData.getSelectedDays();

        /* businessDTO 에 사용자 번호 입력*//*
        // 현재 사용중인 사용자를 지정
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 로그인 되어있는 SecurityContextHolder 안에 있는 AuthDetails 정보를 가져옴
        // AuthDetails 안에 있는 userNo 정보를 가져옴
        int ownerNo;
        ownerNo = ((AuthDetails)currentUser).getLoginUserDTO().getUserNo();*/



        List<ClosedDayDTO> list = new ArrayList<>();

        // 선택된 요일 데이터를 받아 처리하는 코드
        for (String day : selectedDays) {
            System.out.println("선택된 요일: " + day);
            ClosedDayDTO closedDayDTO = new ClosedDayDTO();
            closedDayDTO.setBusinessNo(6);

            // 제공된 string 값을 WeekDay enum으로 변환
            WeekDay weekday = WeekDay.valueOf(day);

            closedDayDTO.setClosedDay(weekday);

            list.add(closedDayDTO);
        }

        int result = roomRegisterService.closedDaysRegister(list);
        String message = "";

        if (result == 0){
            //실패시
            message = "실패";
        }
        else {
            //성공시
            message = "성공";

        }

        // 요청이 정상적으로 처리되었음을 응답으로 전송
        return ResponseEntity.ok(message);
    }
}
