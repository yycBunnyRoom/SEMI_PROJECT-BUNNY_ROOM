package com.yyc.bunnyroom.reservation.controller;

import com.yyc.bunnyroom.roomRegister.model.ClosedDayDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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


}
