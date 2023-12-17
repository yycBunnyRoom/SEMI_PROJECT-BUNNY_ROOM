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
import java.util.Objects;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 관리자 메인 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/adminpage")
    public String adminPage(){
        return "admin/adminpage";
    }

    /**
     * 관리자 페이지 중 회원 관리 페이지로 이동하는 것을 요청을 수행하는 메소드
     * */
    @GetMapping("/member")

    public String member(Model model){
        List<MemberDTO> members = adminService.searchAllMember();
        model.addAttribute("members", members);
        return "admin/member";
    }

    /**
     * 회원을 검색하는 요청을 수행하는 메소드
     * */
    @GetMapping("/search")
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


    /**
     * 해당 회원의 정보를 탈퇴(inactive)로 변경하는 메소드
     * */
    @PostMapping("/withdraw")
    public String withdrawMember(@RequestParam(name = "email") String email, Model model){

        // 정상적으로 email이 전달되지 않았을 경우
        if(Objects.isNull(email)){
            System.out.println("탈퇴할 이메일이 정상적으로 전달되지 않았습니다.");
            model.addAttribute("withdraw", "탈퇴할 이메일이 정상적으로 전달되지 않았습니다.");
            return "admin/member";
        }

        // 정상적으로 전달된 email을 통해 탈퇴 처리를 실행
        System.out.println("EMAIL이 " + email + "인 회원님의 탈퇴 처리를 실행합니다.");
        int result = adminService.withdrawMember(email);

        if(result > 0) {// 정상적인 탈퇴 처리
            model.addAttribute("withdraw", email + "회원이 탈퇴처리되었습니다.");
            return "admin/member";
        }else {// 비정상적인 탈퇴 처리
            System.out.println("정상적으로 동작되지 않았습니다.");
            model.addAttribute("withdraw", "탈퇴처리에 실패하였습니다.");
            return "admin/member";
        }
    }




    /**
     * 관리자 페이지 중 업체 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/business")
    public String business(){
        return "admin/business";
    }

    /**
     * 관리자 페이지 중 공간 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/room")
    public String room(){
        return "admin/room";
    }

    /**
     * 관리자 페이지 중 신고 관리 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/report")
    public String report(){
        return "admin/report";
    }

    /**
     * 관리자 페이지 중 공지사항 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/announcement")
    public String announcement(){
        return "admin/announcement";
    }

    /**
     * 관리자 페이지 중 문의사항 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/inquiry")
    public String inquiry(){
        return "admin/inquiry";
    }

    /**
     * 관리자 페이지 중 관리자 설정 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/setting")
    public String setting(){
        return "admin/setting";
    }
}
