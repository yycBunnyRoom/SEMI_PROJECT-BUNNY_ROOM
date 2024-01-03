package com.yyc.bunnyroom.signup.controller;


import com.yyc.bunnyroom.common.dto.UserDTO;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import com.yyc.bunnyroom.signup.model.dto.SignupDTO;
import com.yyc.bunnyroom.signup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.time.ZonedDateTime;
import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private UserService userService;


    // signup 페이지로 이동하는 컨트롤러
    @GetMapping("/signup")
    public ModelAndView signupUser(ModelAndView modelAndView, @RequestParam("userAuth") String userAuth){
        modelAndView.addObject("userAuth", userAuth);
        modelAndView.setViewName("/signup/signup");
        return modelAndView;
    }


    // 회원가입 방법 선택
    @PostMapping("/signupMethod")
    public ModelAndView signupMethod(ModelAndView modelAndView, @RequestParam("userAuth") String userAuth){
        modelAndView.setViewName("/signup/signup");
        modelAndView.addObject("userAuth", userAuth);
        return modelAndView;
    }

    @GetMapping("/signupMethod")
    public ModelAndView signupMethod(ModelAndView modelAndView){

        modelAndView.setViewName("/signup/signupMethod");
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signupUser(ModelAndView modelAndView, @ModelAttribute @Valid SignupDTO signupDTO, BindingResult bindingResult){

        modelAndView.addObject("userAuth", signupDTO.getUserAuth());
        System.out.println(signupDTO.getUserAuth());
        System.out.println(signupDTO.getUserEmail());
        System.out.println(signupDTO.getUserPhone());



        // 입력된 정보의 유효성 검사, 입력한 정보가 이상하면 alert 반환
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();

            StringBuilder errorMessage = new StringBuilder();


            for (FieldError error : list) {
                errorMessage.append("[")
                        .append(error.getField())
                        .append("] ")
                        .append(": ")
                        .append(error.getDefaultMessage());
                break;
            }

            modelAndView.addObject("error", errorMessage.toString());
            modelAndView.setViewName("/signup/error/validationError"); // 에러가 있는 경우 errorView로 이동
            return modelAndView;
        }


        // 등록하는 사용자의 상태를 활성화("active")로 설정
        signupDTO.setUserStatus("active");

        // 사용자의 등록시간을 현재 시간으로 설정
        ZonedDateTime currentTime = ZonedDateTime.now();
        signupDTO.setUserRegistDate(currentTime);

        int result = userService.registUser(signupDTO);

        String message;

        if (result > 0){
            message = "회원가입이 완료되었습니다";
            modelAndView.setViewName("/security/auth/login");
        } else {
            message = "회원가입이 실패했습니다.";
            modelAndView.setViewName("/signup/signup");
        }
        modelAndView.addObject("message",message);
        return modelAndView;
    }


    // CheckEmail
    @ResponseBody
    @PostMapping("/emailCheck")
    public int emailCheck(@RequestParam("userEmail") String userEmail){
        int result = userService.emailCheck(userEmail);
        return result;
    }

    // CheckPhone
    @ResponseBody
    @PostMapping("/phoneCheck")
    public int phoneCheck(@RequestParam("userPhone") String userPhone){
        int result = userService.phoneCheck(userPhone);
        return result;
    }

    // 비밀번호 찾기
    @GetMapping("/findPasswordMethod")
    public String findPasswordMethod(){
        return "/signup/findPassword/findPasswordMethod";
    }

    @PostMapping("/findPasswordMethod")
    public ModelAndView findPasswordMethodPost(@RequestParam("method") String method, ModelAndView modelAndView){
        if (method.equals("userPhone")){
            modelAndView.addObject("method", "userPhone");
            modelAndView.setViewName("/signup/findPassword/findPassword");
        }
        else if (method.equals("userEmail")){
            modelAndView.addObject("method", "userEmail");
            modelAndView.setViewName("/signup/findPassword/findPassword");
        }
        else {
            String errorMessage = "존재하지 않은 방법입니다.";
            modelAndView.addObject("message",errorMessage);
            modelAndView.setViewName("/signup/findPassword/findPasswordMethod");
        }
        return modelAndView;
    }

    //비밀번호 재설정
    @PostMapping("/resetPassword")
    public ModelAndView resetPassword(@ModelAttribute LoginUserDTO loginUserDTO, ModelAndView modelAndView){

        int result = userService.resetPassword(loginUserDTO);

        if (result == 1){
            modelAndView.addObject("message","비빌번호 바꾸기 성공!!");
            modelAndView.setViewName("/main");
            return modelAndView;
        }
        else {
            modelAndView.addObject("message","비빌번호 바꾸기 실패!!");
            modelAndView.setViewName("/signup/findPassword/findPasswordMethod");
            return modelAndView;
        }
    }





}
