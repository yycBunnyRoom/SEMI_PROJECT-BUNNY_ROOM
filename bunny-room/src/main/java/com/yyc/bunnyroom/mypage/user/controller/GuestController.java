package com.yyc.bunnyroom.mypage.user.controller;

import com.yyc.bunnyroom.mypage.user.dto.ChangePasswordDTO;
import com.yyc.bunnyroom.mypage.user.service.GuestService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
@RequestMapping("/myPage")
public class GuestController {

    @Autowired
    private GuestService guestService;


    /**
     * 게스트 유저 마이페이지로 이동하는 메소드
     * */
    @GetMapping("/search")
    public ModelAndView selectByUserEmail(ModelAndView mv){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();

            String userEmail = userDetails.getLoginUserDTO().getUserEmail();

            LoginUserDTO user = guestService.selectByUserEmail(userEmail);

            if(Objects.isNull(user)){
                throw new NullPointerException();
            }else{
                mv.addObject("user", user);
                mv.setViewName("/myPage/guestSearch");
            }
        }
        return mv;
    }

    /**
     * 게스트 유저가 직접 회원을 탈퇴하는 사유를 작성하는 페이지로 이동하는 메소드
     * */
    @PostMapping("/withdrawReason")
    public ModelAndView withdraw(@RequestParam(name = "userNo")int userNo, ModelAndView mv){
        mv.addObject("userNo", userNo);
        mv.setViewName("/myPage/withdrawReason");
        return mv;


    }

    /**
     * 게스트 유저가 직접 회원을 탈퇴하는 메소드
     * */
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam(name = "userNo")int userNo, @RequestParam(name = "reason") String reason, Model model){
        String update = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int result = guestService.withdrawByUserNo(userNo, reason, update);

        if(result > 0){
            return "/myPage/goodByePage";
        }else {
            return "/myPage/guestSearch";
        }
    }



    @GetMapping("/mypageview")
    public String mypage(@AuthenticationPrincipal AuthDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getLoginUserDTO().getUserEmail());
        return "/myPage/guestUpdatePassword";
    }


//    @PostMapping("/changePassword")
//    public ModelAndView updateUserPassword(
//            @ModelAttribute ChangePasswordDTO changePasswordDTO,
//            HttpSession session,
//            ModelAndView modelAndView
//    ) {
//        // 세션에서 현재 로그인 사용자 정보 가져오기
//        LoginUserDTO loggedInUser = (LoginUserDTO) session.getAttribute("user");
//
//        // 현재 비밀번호 확인
//        if (loggedInUser.getUserPassword().equals(changePasswordDTO.getCurrentPassword())) {
//            // 현재 비밀번호가 일치하면 새 비밀번호로 변경
//            loggedInUser.setUserPassword(changePasswordDTO.getNewPassword());
//
//            // 비밀번호 변경 서비스 호출
//            int result = guestService.updateUserPassword(loggedInUser);
//
//            if (result == 1) {
//                modelAndView.addObject("message2", "비밀번호 변경 성공!!");
//                modelAndView.setViewName("/myPage/guestSearch");
//            } else {
//                modelAndView.addObject("message2", "비밀번호 변경 실패!!");
//                modelAndView.setViewName("/myPage/guestUpdatePassword");
//            }
//        } else {
//            modelAndView.addObject("message2", "현재 비밀번호가 일치하지 않습니다.");
//            modelAndView.setViewName("/myPage/guestUpdatePassword");
//        }
//
//        return modelAndView;
//    }


}

