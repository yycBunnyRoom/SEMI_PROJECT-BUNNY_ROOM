package com.yyc.bunnyroom.admin.controller;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import com.yyc.bunnyroom.admin.service.AdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminpage")
    public String adminPage(){
        return "admin/adminpage";
    }

    @GetMapping("/member")
    public String member(){
        adminService.searchAllMember();
        return "admin/member";
    }

    @GetMapping("/search")
    // 관리자가 회원을 검색하는 메소드
    public String searchMember(@RequestParam String str, Model model){

        if(str.isEmpty()){
            // 회원 전체 검색
            List<MemberDTO> members = adminService.searchAllMember();
            model.addAttribute("members", members);
            return "admin/member";

        }else{
            // 조건에 따른 검색
            List<MemberDTO> members = adminService.searchMember(str);
            model.addAttribute("members", members);
            return "admin/member";
        }
    }






    @GetMapping("/business")
    public String business(){
        return "admin/business";
    }

    @GetMapping("/room")
    public String room(){
        return "admin/room";
    }

    @GetMapping("/report")
    public String report(){
        return "admin/report";
    }

    @GetMapping("/announcement")
    public String announcement(){
        return "admin/announcement";
    }

    @GetMapping("/inquiry")
    public String inquiry(){
        return "admin/inquiry";
    }

    @GetMapping("/setting")
    public String setting(){
        return "admin/setting";
    }
}
