package com.yyc.bunnyroom.common;

import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import com.yyc.bunnyroom.signup.model.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;

@Controller
@RequestMapping(value = {"/", "/main"})
public class MainController {

    @GetMapping
    public ModelAndView main(ModelAndView modelAndView){

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



        modelAndView.setViewName("main");
        return modelAndView;
    }
}
