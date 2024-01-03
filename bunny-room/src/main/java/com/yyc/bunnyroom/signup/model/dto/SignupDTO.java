package com.yyc.bunnyroom.signup.model.dto;

import com.yyc.bunnyroom.common.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class SignupDTO {

    private int userNo;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.\n예) abc@abc.com")
    private String userEmail;


    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 " +
                    "\n8자 ~ 20자의 비밀번호여야 합니다.")
    private String userPassword;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String userNickname;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^010\\d{8}$", message = "유효한 휴대전화 번호 형식이 아닙니다. \n예) 010-1234-5678")
    private String userPhone;

    private UserRole userAuth;
    private String userStatus;
    private ZonedDateTime userRegistDate;
    private ZonedDateTime userUpdateDate;
    private int profileImageNo;


}
