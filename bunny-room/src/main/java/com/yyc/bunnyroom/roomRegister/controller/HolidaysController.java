package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.common.eNum.WeekDay;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.HolidayDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/roomRegister/holidays")
public class HolidaysController {

    @Autowired
    RoomRegisterService roomRegisterService;

    @PostMapping("/register")
    public ResponseEntity<String> registerVacations(@RequestBody List<HolidayDTO> holidayList) {
        System.out.println("받은 데이터: " + holidayList);

        // 받은 데이터를 처리하는 로직을 여기에 추가합니다.
        for (HolidayDTO holiday :
                holidayList) {
            System.out.println("지정 휴가: "+holiday);
            holiday.setBusinessNo(6);

            // register하는 현재 시간을 입력
            ZonedDateTime currentTime = ZonedDateTime.now();
            holiday.setRegistDate(currentTime);

            // 처음 등록하는 상태는 active으로 설정
            holiday.setActiveStatus("active");
        }

        int result = roomRegisterService.holidaysRegister(holidayList);
        String message = "";

        if (result == 0){
            //실패시
            message = "실패";
        }
        else {
            //성공시
            message = "성공";
        }

        // 처리 로직이 완료되면 성공 응답을 보냅니다.
        return ResponseEntity.ok(message);
    }
}
