package com.yyc.bunnyroom.mypage.user.controller;

import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.mypage.user.dto.ChangePasswordDTO;
import com.yyc.bunnyroom.mypage.user.service.GuestService;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/mypages")
public class GuestController {

    @Autowired
    private GuestService guestService;


    @GetMapping("/search")
    public ModelAndView findByUserEmail(ModelAndView mv, @RequestParam String userEmail){

        if(userEmail == null){
            System.out.println("메일 주소는 필수 입니다.");
            mv.addObject("message", "메일 주소는 필수 입니다.");
            mv.setViewName("mypage/errorPage");
            return mv;
        }

        UserDTO user = guestService.findByUserEmail(userEmail);

        if(Objects.isNull(user)){
            throw new NullPointerException();
        }else{
            mv.addObject("user", user);
            mv.setViewName("mypage/guestSearch");
            return mv;

        }
    }

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("user", principal.getName());
        return "mypage/guest";
    }


    @PostMapping("/changePassword")
    public ModelAndView updateUserPassword(
            @ModelAttribute ChangePasswordDTO changePasswordDTO,
            HttpSession session,
            ModelAndView modelAndView
    ) {
        // 세션에서 현재 로그인 사용자 정보 가져오기
        LoginUserDTO loggedInUser = (LoginUserDTO) session.getAttribute("user");

        // 현재 비밀번호 확인
        if (loggedInUser.getUserPassword().equals(changePasswordDTO.getCurrentPassword())) {
            // 현재 비밀번호가 일치하면 새 비밀번호로 변경
            loggedInUser.setUserPassword(changePasswordDTO.getNewPassword());

            // 비밀번호 변경 서비스 호출
            int result = guestService.updateUserPassword(loggedInUser);

            if (result == 1) {
                modelAndView.addObject("message2", "비밀번호 변경 성공!!");
                modelAndView.setViewName("/main");
            } else {
                modelAndView.addObject("message2", "비밀번호 변경 실패!!");
                modelAndView.setViewName("/mypage/changePasswordPage");
            }
        } else {
            modelAndView.addObject("message2", "현재 비밀번호가 일치하지 않습니다.");
            modelAndView.setViewName("/mypage/changePasswordPage");
        }

        return modelAndView;
    }
}

