package com.yyc.bunnyroom.roomRegister.controller;

import com.yyc.bunnyroom.common.UserRole;
import com.yyc.bunnyroom.common.eNum.WeekDay;
import com.yyc.bunnyroom.roomRegister.dao.RoomRegisterMapper;
import com.yyc.bunnyroom.roomRegister.model.*;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/roomRegister")
public class RoomRegisterController {

    @Autowired
    RoomRegisterService roomRegisterService;




    /*          VIEWS           */


    @GetMapping("/hostMainView")
    public String hostMainView(){
        return "/roomRegister/view/hostMainView";
    }

    /* 사업체 관리 페이지*/
    @GetMapping("/hostBusinessView")
    public String hostBusinessView(){
        return "/roomRegister/view/hostBusinessView";
    }


    /* 업체등록 페이지 */
    @GetMapping("/businessRegisterForm")
    public ModelAndView businessRegisterForm(ModelAndView modelAndView){

        modelAndView.setViewName("/roomRegister/form/businessRegisterForm");

        return modelAndView;
    }

    /* 휴무 등록 페이지 */
    @GetMapping("/dayOffRegisterForm")
    public ModelAndView dayOffRegisterForm(@RequestParam(value = "businessNo", required = false) int businessNo,
                                     ModelAndView modelAndView){

        modelAndView.addObject("businessNo",businessNo);
        modelAndView.setViewName("/roomRegister/form/dayOffRegisterForm");

        return modelAndView;
    }

    /* 방 등록 페이지 */
    @GetMapping("/roomRegisterForm")
    public ModelAndView roomRegisterForm(@RequestParam(value = "businessNo", required = false) int businessNo,
                                         ModelAndView modelAndView){

        System.out.println("roomRegisterFrom 진입전: "+businessNo);
        modelAndView.addObject("businessNo",businessNo);
        modelAndView.setViewName("/roomRegister/form/roomRegisterForm");

        return modelAndView;
    }

    /* 예약 시간 관리 페이지로 이동 */
    @GetMapping("/timeSchedule")
    public ModelAndView goToTimeSchedule(@RequestParam(value = "businessNo", required = false) int businessNo,
                                         ModelAndView modelAndView){

        System.out.println("Time schedule arrived");

        modelAndView.addObject("businessNo",businessNo);
        modelAndView.setViewName("/roomRegister/form/timeScheduleForm");
        return modelAndView;
    }





    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {

        if (error == null){
            return "/main";
        }

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/security/auth/login";
    }



    /*          Model Attributes            */
    @ModelAttribute("categories")
    public List<BusinessCategoryDTO> categories(){
        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        return roomRegisterService.selectAllBusinessCategory();
    }

    @ModelAttribute("weekDays")
    public String[] weekDays(){
        WeekDay[] weekDayList = WeekDay.values();
        String[] weekdays = new String[weekDayList.length];

        for (int i = 0; i < weekDayList.length; i++) {
            weekdays[i] = weekDayList[i].getDescription();
        }
        return weekdays;
    }

//    @ModelAttribute("weekDays")
//    public WeekDay[]weekDays(){return WeekDay.values();}

    @ModelAttribute("roomOptions")
    public List<RoomOptionDTO> roomOptions(){
        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        return roomRegisterService.selectAllRoomOptions();
    }



}
