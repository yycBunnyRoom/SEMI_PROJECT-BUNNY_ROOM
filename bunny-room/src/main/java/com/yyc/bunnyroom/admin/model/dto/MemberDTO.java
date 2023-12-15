package com.yyc.bunnyroom.admin.model.dto;

import java.time.LocalDateTime;

public class MemberDTO {

    private int userNo;

    private String email;

    private String nickname;

    private String phone;

    private String status;

    private String auth;

    private LocalDateTime userRegistDate;

    private LocalDateTime userUpdateDate;

    public MemberDTO() {
    }

    public MemberDTO(int userNo, String email, String nickname, String phone, String status, String auth, LocalDateTime userRegistDate, LocalDateTime userUpdateDate) {
        this.userNo = userNo;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.status = status;
        this.auth = auth;
        this.userRegistDate = userRegistDate;
        this.userUpdateDate = userUpdateDate;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
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

    @Override
    public String toString() {
        return "MemberDTO{" +
                "userNo=" + userNo +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", auth='" + auth + '\'' +
                ", userRegistDate=" + userRegistDate +
                ", userUpdateDate=" + userUpdateDate +
                '}';
    }
}
