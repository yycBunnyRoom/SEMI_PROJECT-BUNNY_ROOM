package com.yyc.bunnyroom.common;

import com.yyc.bunnyroom.security.auth.model.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
              userAuth ="관리자";
           }else if (((AuthDetails)principal).getLoginUserDTO().getUserAuth() == UserRole.HOST){
               userAuth ="호스트";
           } else if (((AuthDetails)principal).getLoginUserDTO().getUserAuth() == UserRole.GUEST) {
               userAuth ="게스트";
           }
           userNickname = ((AuthDetails)principal).getLoginUserDTO().getUserNickname();
        } else {
            userAuth ="인증되지 않은";
            userNickname="사용자";
        }


        modelAndView.addObject("userAuth",userAuth);
        modelAndView.addObject("userNickname",userNickname);


        modelAndView.setViewName("main");
        return modelAndView;
    }
}
