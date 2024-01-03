package com.yyc.bunnyroom.common.controller;

import com.yyc.bunnyroom.common.UserRole;
import com.yyc.bunnyroom.roomRegister.model.BusinessCategoryDTO;
import com.yyc.bunnyroom.roomRegister.model.RoomDTO;
import com.yyc.bunnyroom.roomRegister.service.RoomRegisterService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/main"})
public class MainController {

    @Autowired
    RoomRegisterService roomRegisterService;

    @GetMapping
    public ModelAndView main(ModelAndView modelAndView,
                             @RequestParam(name = "message", required = false) String message){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userAuth="";
        String userNickname="";

        if (principal instanceof AuthDetails) {
           if (((AuthDetails)principal).getLoginUserDTO().getUserAuth() == UserRole.ADMIN){
              userAuth ="ADMIN";
           }else if (((AuthDetails)principal).getLoginUserDTO().getUserAuth() == UserRole.HOST){
               userAuth ="HOST";
           } else if (((AuthDetails)principal).getLoginUserDTO().getUserAuth() == UserRole.GUEST) {
               userAuth ="GUEST";
           }
           userNickname = ((AuthDetails)principal).getLoginUserDTO().getUserNickname();
        } else {
            userAuth = null;
            userNickname= null;
        }

        modelAndView.addObject("userAuth",userAuth);
        modelAndView.addObject("userNickname",userNickname);
        modelAndView.addObject("message", message); // 메시지 추가

        modelAndView.setViewName("/main");
        return modelAndView;
    }


    @GetMapping("/test2")
    public String test2(){
        return "/test/test2";
    }

    @GetMapping("/test4")
    public String test4(){
        return "/test/test4";
    }



    // 메인 창에 카테고리 코드를 가져감
    @ModelAttribute("categories")
    public List<BusinessCategoryDTO> categories(){
        // DB 에 있는 카테고리를 가져와서 같이 보낸다
        return roomRegisterService.selectAllBusinessCategory();
    }
    @ModelAttribute("latestRooms")
    public List<RoomDTO> latestRooms(){
        return roomRegisterService.getLatestRoomsByLimit10();
    }

}
