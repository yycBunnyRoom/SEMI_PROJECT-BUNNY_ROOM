package com.yyc.bunnyroom.admin.controller;

import com.yyc.bunnyroom.admin.model.dto.MemberDTO;
import com.yyc.bunnyroom.admin.service.AdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        // 관리자 메인 페이지로 이동
        return "admin/adminpage";
    }

    @GetMapping("/member")
    public String member(){
        // 관리자 회원 관리 페이지로 이동
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
        // 관리자 업체 관리 페이지로 이동
        return "admin/business";
    }

    @GetMapping("/room")
    public String room(){
        // 관리자 공간 관리 페이지로 이동
        return "admin/room";
    }

    @GetMapping("/report")
    public String report(){
        // 관리자 신고 관리 페이지로 이동
        return "admin/report";
    }

    @GetMapping("/announcement")
    public String announcement(){
        // 관리자 공지사항 페이지로 이동
        return "admin/announcement";
    }

    @GetMapping("/inquiry")
    public String inquiry(){
        // 관리자 문의사항 페이지로 이동
        return "admin/inquiry";
    }

    @GetMapping("/setting")
    public String setting(){
        // 관리자 설정 페이지로 이동
        return "admin/setting";
    }
}
