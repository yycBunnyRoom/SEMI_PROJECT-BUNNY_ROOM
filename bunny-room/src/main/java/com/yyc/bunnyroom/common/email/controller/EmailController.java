package com.yyc.bunnyroom.common.email.controller;

import com.yyc.bunnyroom.common.email.dto.EmailCheckDTO;
import com.yyc.bunnyroom.common.email.dto.EmailRequestDTO;
import com.yyc.bunnyroom.common.email.service.EmailSendService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailSendService emailService;

    @PostMapping("/mailSend")
    public String mailSend(@RequestParam("userEmail") String userEmail) {

        System.out.println(userEmail);

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setEmail(userEmail);


        System.out.println("이메일 인증 요청이 들어옴");
        System.out.println("이메일 인증 이메일 :" + emailRequestDTO.getEmail());
        return emailService.joinEmail(emailRequestDTO.getEmail());
    }

    @PostMapping("/mailAuthCheck")
    public int authCheck(@RequestParam("userEmail")String userEmail, @RequestParam("authNum") String authNum){

        // 받아온 userEmail 과 authNum 을 emailCheckDTO에 저장
        EmailCheckDTO emailCheckDTO = new EmailCheckDTO();

        emailCheckDTO.setUserEmail(userEmail);
        emailCheckDTO.setAuthNum(authNum);

        Boolean emailConfirmed = emailService.checkAuthNum(emailCheckDTO.getUserEmail(), emailCheckDTO.getAuthNum());

        if(emailConfirmed){
            return 1;
        }else {
            return 0;
        }
    }

}