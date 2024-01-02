package com.yyc.bunnyroom.common.exception;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e, Model model){
        String message = "Global에서 nullPointerException 처리함";

        return showExceptionView(model,e,message);
    }

    @ExceptionHandler(Exception.class)
    public String userExceptionHandler(Exception e, Model model){
        String message = "Global에서 nullPointerException 처리함";

        return showExceptionView(model,e,message);
    }




    public String showExceptionView(Model model, Exception e, String message){
        System.out.println(message);
        System.out.println(e.getClass());

        model.addAttribute("message",message);
        model.addAttribute("message2",e.getClass());
        model.addAttribute("message3",e.getMessage());

        return "/common/view/errorView";
    }


    // 권한이 없는 사용자가 해당 페이지에 접근을 할때, 메세지를 띄우고 메인 페이지로 이동 시킨다
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ModelAndView handleAccessDeniedException(ModelAndView modelAndView) {
//        String message = "해당 페이지에 대한 권한이 없습니다.";
//
//        modelAndView.addObject("message",message);
//        modelAndView.setViewName("redirect:/main");
//        return modelAndView;
//    }


}
