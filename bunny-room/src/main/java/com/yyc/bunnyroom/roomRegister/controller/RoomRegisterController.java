package com.yyc.bunnyroom.roomRegister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/roomRegister")
public class RoomRegisterController {


    // 업체등록 페이지로 이동하는 컨드롤러
    @GetMapping("/businessRegisterView")
    public String businessRegisterView(){
        return "/roomRegister/view/businessRegisterView";
    }


}
