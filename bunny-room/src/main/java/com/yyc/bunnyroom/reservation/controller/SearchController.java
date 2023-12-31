package com.yyc.bunnyroom.reservation.controller;

import com.yyc.bunnyroom.reservation.model.KeywordDTO;
import com.yyc.bunnyroom.roomRegister.model.*;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    RoomRegisterService roomRegisterService;

    @GetMapping("/category")
    public ModelAndView searchByCategory(@RequestParam(value = "businessCategoryName")String businessCategoryName,
                                           ModelAndView modelAndView){

        // 카테고리 이름으로 DB에서 해당하는 방 리스트를 가져옴
        List<RoomDTO> rooms = roomRegisterService.getRoomsByCategory(businessCategoryName);

        System.out.println("roomDTO가 들어왔는가? : "+rooms);



        if (Objects.isNull(rooms)){
            modelAndView.addObject("message", "해당 카테고리로 등록된 방이 없습니다.");
            modelAndView.setViewName("/main");
            return modelAndView;
        }
        else {
            modelAndView.addObject("rooms", rooms);
            modelAndView.setViewName("/reservation/view/searchResultView");
            return modelAndView;
        }

    }

    @GetMapping("/keyword")
    public ModelAndView searchByKeyword(@RequestParam(value = "keyword")String keyword,
                                         ModelAndView modelAndView) {

        List<RoomDTO> rooms = new ArrayList<>();

        // 아무것도 입력하지 않는 경우 모두 가져옴
        if (keyword.isEmpty() || keyword.equals(" ")){
            rooms = roomRegisterService.getAllRooms();
        }
        else {
            String param = "%" + keyword + "%";
            KeywordDTO keywordDTO = new KeywordDTO();
            // KeywordDTO 클래스의 모든 필드에 접근
            Field[] fields = KeywordDTO.class.getDeclaredFields();

            // 각 필드에 값을 주입
            for (Field field : fields) {
                if (field.getType() == String.class) { // 필드가 String 타입인 경우에만 설정
                    try {
                        field.setAccessible(true); // private 필드에 접근하기 위해 필요
                        field.set(keywordDTO, param); // 필드에 값을 설정
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 카테고리 이름으로 DB에서 해당하는 방 리스트를 가져옴
            rooms = roomRegisterService.getRoomsByKeyword(keywordDTO);
        }

        int total=0;
        System.out.println("roomDTO가 들어왔는가? : " + rooms);
        for (RoomDTO room :
                rooms) {
            total++;
        }
        System.out.println("총 검색된 결과: "+total);


        modelAndView.addObject("rooms", rooms);
        modelAndView.setViewName("/reservation/view/searchResultView");
        return modelAndView;
    }

    /* 방 상세 페이지로 이동 */
    @GetMapping("/roomDetail")
    public ModelAndView getBusinessDetails(@RequestParam(value = "roomNo")String roomNo,
                                           ModelAndView modelAndView) {

        System.out.println("이동할 상세페이지의 방번호: "+roomNo);

        RoomDTO roomDetails = roomRegisterService.getRoomDetails(Integer.parseInt(roomNo));

        System.out.println("roomDTO 정보가 들어왔는지 확인: "+roomDetails);

        // 해당 방과 관련된 사업체 정보를 가져온다
        BusinessDTO relatedBusinessInfo = roomRegisterService.getBusinessInfoByRoomNo(Integer.parseInt(roomNo));

        System.out.println("businessDTO 정보가 들어왔는지 확인: "+relatedBusinessInfo);


        /* 해당 방의 휴무 정보와 시간 스케줄 정보를 가져온다 */

        // 먼저 해당 방의 사업체 번호 확인
        int businessNo = relatedBusinessInfo.getBusinessNo();

        // 정기 휴무를 가져온다
        List<ClosedDayDTO> closedDays = roomRegisterService.getAllClosedDays(businessNo);

        System.out.println("정기 휴일 제대로 가져왔는지 확인: "+closedDays);

        // 지정 휴무를 가져온다
        List<HolidayDTO> holidays = roomRegisterService.getHolidaysByBusinessNo(businessNo);

        System.out.println("지정 휴일 제대로 가져왔는지 확인: "+holidays);

        modelAndView.addObject("closedDays",closedDays);
        modelAndView.addObject("holidays",holidays);

        // 타임 스케줄을 가져온다
        List<TimeUnitScheduleDTO> timeUnits = roomRegisterService.getTimeUnitsByBusinessNo(businessNo);

        System.out.println("타임 스케줄이 제대로 왔는지 확인: "+timeUnits);

        modelAndView.addObject("timeUnits",timeUnits);

        // 선택한 appliedOptions를 같이 보냄
        List<AppliedOptionDTO> appliedOptions = roomRegisterService.getAppliedOptions(Integer.parseInt(roomNo));

        System.out.println(roomNo+"번방의 옵션들: "+appliedOptions);


        modelAndView.setViewName("/reservation/detail/roomDetail");
        modelAndView.addObject("relatedBusinessInfo",relatedBusinessInfo);
        modelAndView.addObject("appliedOptions",appliedOptions);
        modelAndView.addObject("roomDetails",roomDetails);
        return modelAndView;
    }
}
