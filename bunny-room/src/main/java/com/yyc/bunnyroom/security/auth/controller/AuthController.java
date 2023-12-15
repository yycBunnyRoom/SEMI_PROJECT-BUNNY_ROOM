package com.yyc.bunnyroom.security.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/security/auth")
public class AuthController {

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView modelAndView){
        modelAndView.setViewName("/security/auth/login");
        return modelAndView;
    }

    @GetMapping("/fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView modelAndView){
        modelAndView.addObject("message",message);
        modelAndView.setViewName("/security/auth/fail");
        return modelAndView;
    }


    // logOut
    @GetMapping("/logout")
    public ModelAndView logOut(ModelAndView modelAndView){
        modelAndView.setViewName("/main");
        return modelAndView;
    }
}
