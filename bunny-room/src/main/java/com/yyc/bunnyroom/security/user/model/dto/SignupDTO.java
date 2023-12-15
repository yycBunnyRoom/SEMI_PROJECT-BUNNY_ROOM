package com.yyc.bunnyroom.security.user.model.dto;

import com.yyc.bunnyroom.common.UserRole;

import java.time.LocalDateTime;
import java.util.Date;

public class SignupDTO {

    private int userNo;
    private String userEmail;
    private String userPassword;
    private String userNickname;
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
