package com.yyc.bunnyroom.adminBusiness.controller;

import com.yyc.bunnyroom.adminBusiness.model.dto.BusinessAllInfoDTO;
import com.yyc.bunnyroom.adminBusiness.service.AdminRoomService;
import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.HolidayDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/business/")
@RequiredArgsConstructor
public class AdminRoomController {

    private final AdminRoomService adminRoomService;

    /**
     * 해당 업체의 상세페이지를 요청하는 메소드
     * */
    @GetMapping("{businessNo}")
    public String businessDetail(@PathVariable("businessNo") int businessNo, Model model){
        // 해당 방 사업체 정보 가져오기
        BusinessAllInfoDTO businessInfo = adminRoomService.searchBusinessInfo(businessNo);
        // 해당 방과 관련된 방 정보 가져오기
        List<RoomDTO> roomInBusinessInfo = adminRoomService.searchRoomInBusiness(businessNo);
        // 해당 방과 관련된 휴일 정보 가져오기
        List<ClosedDayDTO> closedDayInBusinessInfo = adminRoomService.searchClosedDayInBusiness(businessNo);
        List<HolidayDTO> holidayInBusinessInfo = adminRoomService.searchHolidayInBusiness(businessNo);

        model.addAttribute("business", businessInfo);
        model.addAttribute("room", roomInBusinessInfo);
        model.addAttribute("closedDay", closedDayInBusinessInfo);
        model.addAttribute("holiday", holidayInBusinessInfo);

        return "admin/business/detail/businessDetail";
    }

//    @PostMapping("/roomDelete")
//    @ResponseBody
//    public ResponseEntity<String> deleteRoom(){
//
//    }
}
