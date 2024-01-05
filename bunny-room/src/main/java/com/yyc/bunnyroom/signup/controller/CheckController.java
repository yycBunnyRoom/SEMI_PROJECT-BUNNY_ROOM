package com.yyc.bunnyroom.signup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.yyc.bunnyroom.reservation.service.ReservationService;
import com.yyc.bunnyroom.signup.model.dto.SignupDTO;
import com.yyc.bunnyroom.signup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    UserService userService;

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<?> checkEmail(@PathVariable("userEmail") String userEmail){

        // DB에 중복되는 이메일이있는지 확인, 중복되면 true를 반환
        boolean isDuplicate = userService.checkEmailByUserEmail(userEmail);

        return ResponseEntity.ok("{\"duplicate\": " + isDuplicate + "}");
    }

    @GetMapping("/nickname/{userNickname}")
    public ResponseEntity<?> checkNickname(@PathVariable("userNickname") String userNickname){

        // DB에 중복되는 이메일이있는지 확인, 중복되면 true를 반환
        boolean isDuplicate = userService.checkEmailByUserNickname(userNickname);

        return ResponseEntity.ok("{\"duplicate\": " + isDuplicate + "}");
    }


    @PostMapping("/register")
    public ResponseEntity<?> registUser(@RequestBody @Valid SignupDTO newUser, BindingResult bindingResult) throws JsonProcessingException {

        System.out.println(newUser);

        // jsonString 선언
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;

        Boolean result;

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

            String message = errorMessage.toString();
            result = false;

            // ObjectMapper를 사용해서 JSON 객체를 만들고 그안에 message랑 result 할당
            jsonString = objectMapper.writeValueAsString(
                    Map.of("message",message,"result",result)
            );

            return ResponseEntity.ok(jsonString);
        }

        /*필요 값을 입력*/

        // 등록하는 사용자의 상태를 활성화("active")로 설정
        newUser.setUserStatus("active");

        // 사용자의 등록시간을 현재 시간으로 설정
        ZonedDateTime currentTime = ZonedDateTime.now();
        newUser.setUserRegistDate(currentTime);

        int registResult = userService.registUser(newUser);

        String message;

        if (registResult > 0){
            message = "회원가입 성공!";
            result = true;
        } else {
            message = "회원가입 실패!";
            result = false;
        }

        jsonString = objectMapper.writeValueAsString(
                Map.of("message",message,"result",result)
        );
        return ResponseEntity.ok(jsonString);
    }
}
