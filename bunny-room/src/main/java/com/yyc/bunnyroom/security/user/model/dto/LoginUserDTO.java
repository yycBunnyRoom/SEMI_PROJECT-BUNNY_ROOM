package com.yyc.bunnyroom.security.user.model.dto;

import com.yyc.bunnyroom.common.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LoginUserDTO {
    // 회원의 pk 번호
    private int userNo;

    // varchar 255 - 회원의 이메일 - 로그인할때 필요함, 이것을 기준으로 회원의 loginDTO를 가져옴
    private String userEmail;

    // varchar 12 - 회원의 비밀번호 - 비밀번호는 10자까지 받는다
    private String userPassword;

    // varchar 50 - 회원의 닉네임
    private String userNickname;

    // varchar 15 - 01012345678 , 혹은 010-1234-5678 이외의 형식은 다 반려(지원하지 않는 형식의 전화번호입니다)
    private String userPhone;

    // varchar 10 - 유저의 권한을 설정 - ADMIN , GUEST , HOST
    private UserRole userAuth;

    // varchar 10 - 유저의 상태를 보여줌 - active , black , inactive(탈퇴)
    private String userStatus;

    // DATE - 회원등록일 - 언제 회원이 등록했는지
    private LocalDateTime userRegistDate;

    // DATE - 회원수정일 - 회원정보가 변화할 시 마지막 날짜 / 블랙리스트 등 회원정보 수정의 로직에 활용
    private LocalDateTime userUpdateDate;

    // int - 회원프로필 이미지 테이블의 pk 값을 참조하는 번호
    private int profileImageNo;



    public LoginUserDTO() {
    }

    public LoginUserDTO(int userNo, String userEmail, String userPassword, String userNickname, String userPhone,
                        UserRole userAuth, String userStatus, LocalDateTime userRegistDate, LocalDateTime userUpdateDate, int profileImageNo) {
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
        return "LoginUserDTO{" +
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

    public List<String> getRole(){
        if (this.userAuth.getRole().length()>0){
            return Arrays.asList(this.userAuth.getRole().split(","));
        }
        return new ArrayList<>();
    }
}
