package com.yyc.bunnyroom.signup.controller;


import com.yyc.bunnyroom.signup.model.dto.SignupDTO;
import com.yyc.bunnyroom.signup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

        // 비밀번호 확인
        Object password1 = modelAndView.getModel().get("UserPasswordConfirm");
        String password2 = (password1 != null) ? password1.toString() : null ;
        if (! signupDTO.getUserPassword().equals(password2)){

            String error = "입력하신 비밀번호가 다릅니다.";
            modelAndView.addObject("error", error);
            modelAndView.setViewName("/signup/error/validationError"); // 에러가 있는 경우 errorView로 이동
            return modelAndView;
        }


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



//        if (errors.hasErrors()) {
//
//            // 유효성 통과 못한 필드와 메시지를 핸들링
//            Map<String, String> validatorResult = userService.validateHandling(errors);
//
//            for (String key : validatorResult.keySet()) {
//                modelAndView.addObject(key, validatorResult.get(key));
//            }
//
//            modelAndView.setViewName("/signup/signup");
//            return modelAndView;
//        }


        // 등록하는 사용자의 상태를 활성화("active")로 설정
        signupDTO.setUserStatus("active");

        // 사용자의 등록시간을 현재 시간으로 설정
        LocalDateTime currentTime = LocalDateTime.now();
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


    // Validation 예외 처리
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ModelAndView handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
//        System.out.println("바인딩 에러 발생함");
//
//        BindingResult bindingResult = exception.getBindingResult();
//        StringBuilder errorMessage = new StringBuilder();
//
//        for (FieldError error : bindingResult.getFieldErrors()) {
//            errorMessage.append("[")
//                    .append(error.getField())
//                    .append("] ")
//                    .append(":")
//                    .append(error.getDefaultMessage());
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("error", errorMessage.toString());
//        modelAndView.setViewName("/signup/error/validationError"); // 에러가 있는 경우 validationError 이동
//
//        return modelAndView;
//    }


}
