package com.yyc.bunnyroom.mypage.user.controller;

import com.yyc.bunnyroom.mypage.user.dto.ReservationListDTO;
import com.yyc.bunnyroom.mypage.user.service.GuestService;
import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String withdraw(@RequestParam(name = "userNo")int userNo, @RequestParam(name = "reason") String reason, RedirectAttributes redirectAttributes,
                           HttpServletRequest request){
        String update = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        int result = guestService.withdrawByUserNo(userNo, reason, update);

        if(result > 0){
            // 탈퇴 성공시 세션 만료
            // 현재 사용자 보안 컨텍스트 제거
            SecurityContextHolder.clearContext();
            // 세션 만료
            request.getSession().invalidate();
            return "/myPage/goodByePage";
        }else {
            redirectAttributes.addFlashAttribute("message", "탈퇴에 실패하였습니다.");
            return "/myPage/withdrawReason";
        }
    }

    /**
     * 게스트 회원의 닉네임을 변경하는 요청을 수행하는 메소드
     * */
    @PostMapping("/changeNickname")
    @ResponseBody
    public Map<String, Object> changeNickname(@RequestParam(name = "userNo")int userNo, @RequestParam(name = "nickname")String nickName){
        boolean success = guestService.changeNicknameByUserNo(userNo, nickName);

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("nickname", nickName);

        return result;
    }

    /**
     * 게스트 회원의 연락처를 변경하는 요청을 수행하는 메소드
     * */
    @PostMapping("/changePhone")
    @ResponseBody
    public Map<String, Object> changePhone(@RequestParam(name = "userNo")int userNo, @RequestParam(name = "phone")String phone, Model model){
        boolean success = guestService.changePhoneByUserNo(userNo, phone);

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("phone", phone);
        return result;
    }

    /**
     * 게스트 비밀번호 변경 페이지로 이동하는 메소드
     * */
    @PostMapping("/changePasswordPage")
    public ModelAndView changePasswordPage(@RequestParam("userNo")int userNo, @RequestParam("password")String password, ModelAndView mv){
        mv.addObject("userNo", userNo);
        mv.addObject("password", password);
        mv.setViewName("/myPage/passwordChanger");

        return mv;
    }

    /**
     * 게스트가 비밀번호를 변경하는 메소드
     * */
    @PostMapping("/passwordChanger")
    public String passwordChanger(@RequestParam("userNo")int userNo, @RequestParam("password")String password,
                                  @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
                                  @RequestParam("newPasswordRe")String newPasswordRe, Model model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        model.addAttribute("userNo", userNo);
        model.addAttribute("password", password);
        model.addAttribute("oldPassword", oldPassword);
        model.addAttribute("newPassword", newPassword);
        model.addAttribute("newPasswordRe", newPasswordRe);
        int minPass = 8;
        int maxPass = 20;
        int result = 0;

        if(!passwordEncoder.matches(oldPassword, password)){ // 현재 비밀번호가 틀렸을 때
            model.addAttribute("message", "현재 비밀번호가 올바르지 않습니다.");
            return "/myPage/passwordChanger";
        }else if (oldPassword.equals(newPassword)){ // 구 비밀번호와 신 비밀번호가 동일할 때
            model.addAttribute("message", "입력하신 비밀번호가 전과 동일합니다.");
            return "/myPage/passwordChanger";
        }else if(!newPassword.equals(newPasswordRe)){ // 새 비밀번호와 확인번호가 다를 때
            model.addAttribute("message", "새 비밀번호와 비밀번호 확인 번호가 다릅니다.");
            return "/myPage/passwordChanger";
        }else if(newPassword.length() < minPass || newPassword.length() > maxPass){ // 비밀번호 수 제한을 어길 때
            model.addAttribute("message", "새 비밀번호는 최소 " + minPass + "자 이상이고, 최대 " + maxPass + "자 이하여야 합니다.");
            return "/myPage/passwordChanger";
        }else {
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            result = guestService.changePasswordByUserNo(userNo, encodedNewPassword);
            model.addAttribute("message", "비밀번호가 정상적으로 변경되었습니다.");
            return "/myPage/guestSearch";
        }
    }

    /**
     * 마이페이지의 예약리스트 페이지로 이동하는 요청을 수행하는 메소드
     * */
    @GetMapping("/reservationList")
    public String reservationList(Model model){
        showReservation(model);
        return "/myPage/reservationList";
    }

    /**
     * 마이페이지 예약리스트를 조회하는 요청을 수행하는 메소드
     * */
    @GetMapping("/showReservation")
    public Model showReservation(Model model){
        // 시큐리티에서 현재 유저 정보를 취득

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userNo;
        if (authentication != null && authentication.isAuthenticated()) {
            AuthDetails userDetails = (AuthDetails) authentication.getPrincipal();
            userNo = userDetails.getLoginUserDTO().getUserNo();
        }else {
            model.addAttribute("message", "정상적으로 인증되지 않아 리스트를 조회할 수 없습니다.");
            return model;
        }

        // 예약리스트 조회
        List<ReservationListDTO> reservationList = guestService.showReservation(userNo);

        model.addAttribute("reservationList", reservationList);
        return model;
    }
}

