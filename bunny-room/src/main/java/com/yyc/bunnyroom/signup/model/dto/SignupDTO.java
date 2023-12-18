package com.yyc.bunnyroom.signup.model.dto;

import com.yyc.bunnyroom.common.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;


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
    @Pattern(regexp = "^010\\d{8}$", message = "유효한 휴대전화 번호 형식이 아닙니다. \n예) 01012345678")
    private String userPhone;
    private UserRole userAuth;
    private String userStatus;
    private LocalDateTime userRegistDate;
    private LocalDateTime userUpdateDate;
    private int profileImageNo;

    public SignupDTO() {
    }

    public SignupDTO(int userNo, String userEmail, String userPassword, String userNickname,
                     String userPhone, UserRole userAuth, String userStatus, LocalDateTime userRegistDate, LocalDateTime userUpdateDate, int profileImageNo) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userPhone = userPhone;
        this.userAuth = userAuth;
        this.userStatus = userStatus;
        this.userRegistDate = userRegistDate;
        this.userUpdateDate = userUpdateDate;
        this.profileImageNo = profileImageNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public UserRole getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserRole userAuth) {
        this.userAuth = userAuth;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getUserRegistDate() {
        return userRegistDate;
    }

    public void setUserRegistDate(LocalDateTime userRegistDate) {
        this.userRegistDate = userRegistDate;
    }

    public LocalDateTime getUserUpdateDate() {
        return userUpdateDate;
    }

    public void setUserUpdateDate(LocalDateTime userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    public int getProfileImageNo() {
        return profileImageNo;
    }

    public void setProfileImageNo(int profileImageNo) {
        this.profileImageNo = profileImageNo;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "userNo=" + userNo +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAuth=" + userAuth +
                ", userStatus='" + userStatus + '\'' +
                ", userRegistDate=" + userRegistDate +
                ", userUpdateDate=" + userUpdateDate +
                ", profileImageNo=" + profileImageNo +
                '}';
    }
}
