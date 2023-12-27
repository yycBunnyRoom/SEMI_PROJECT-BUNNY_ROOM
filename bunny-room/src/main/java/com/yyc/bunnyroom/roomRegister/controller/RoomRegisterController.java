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

//        // DB 에 있는 카테고리를 가져와서 같이 보낸다
//        List<BusinessCategoryDTO> businessCategoryList = roomRegisterService.selectAllBusinessCategory();
//
//        modelAndView.addObject("businessCategoryList",businessCategoryList);

        modelAndView.setViewName("/roomRegister/form/businessRegisterForm");

        return modelAndView;
    }

    /* 휴무 등록 페이지 */
    @GetMapping("/dayOffRegisterForm")
    public String dayOffRegisterForm(ModelAndView modelAndView){
        ClosedDayDTO closedDayDTO = new ClosedDayDTO();
//        HolidayDTO holiday = new HolidayDTO();

        modelAndView.addObject("closedDayDTO",closedDayDTO);
//        modelAndView.addObject("holiday",holiday);


        return "/roomRegister/form/dayOffRegisterForm";
    }

    /* 방 등록 페이지 */
    @GetMapping("/roomRegisterForm")
    public ModelAndView roomRegisterForm(ModelAndView modelAndView){

        modelAndView.setViewName("/roomRegister/form/roomRegisterForm");
        return modelAndView;
    }






    /*          Model Attributes            */
    @ModelAttribute("categories")
    public List<BusinessCategoryDTO> categories(){
        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        return roomRegisterService.selectAllBusinessCategory();
    }

    @ModelAttribute("weekDays")
    public WeekDay[] weekDays(){
        return WeekDay.values();
    }

    @ModelAttribute("roomOptions")
    public List<RoomOptionDTO> roomOptions(){
        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        return roomRegisterService.selectAllRoomOptions();
    }



}
