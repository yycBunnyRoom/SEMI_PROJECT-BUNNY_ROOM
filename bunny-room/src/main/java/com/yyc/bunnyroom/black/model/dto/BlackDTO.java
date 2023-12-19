package com.yyc.bunnyroom.black.model.dto;

import java.time.ZonedDateTime;

public class BlackDTO {

    private int blackNo;

    private int userNo;

    private String auth;

    private String email;

    private String nickname;

    private String phone;

    private String blackReason;

    private ZonedDateTime blackRegistDate;

    private String blackStatus;

    private ZonedDateTime blackUpdateDate;

    private ZonedDateTime blackSentencePeriod;

    public BlackDTO() {
    }

    public BlackDTO(int blackNo, int userNo, String auth, String email, String nickname, String phone, String blackReason, ZonedDateTime blackRegistDate, String blackStatus, ZonedDateTime blackUpdateDate, ZonedDateTime blackSentencePeriod) {
        this.blackNo = blackNo;
        this.userNo = userNo;
        this.auth = auth;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.blackReason = blackReason;
        this.blackRegistDate = blackRegistDate;
        this.blackStatus = blackStatus;
        this.blackUpdateDate = blackUpdateDate;
        this.blackSentencePeriod = blackSentencePeriod;
    }

    public int getBlackNo() {
        return blackNo;
    }

    public void setBlackNo(int blackNo) {
        this.blackNo = blackNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
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

    public String getBlackReason() {
        return blackReason;
    }

    public void setBlackReason(String blackReason) {
        this.blackReason = blackReason;
    }

    public ZonedDateTime getBlackRegistDate() {
        return blackRegistDate;
    }

    public void setBlackRegistDate(ZonedDateTime blackRegistDate) {
        this.blackRegistDate = blackRegistDate;
    }

    public String getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(String blackStatus) {
        this.blackStatus = blackStatus;
    }

    public ZonedDateTime getBlackUpdateDate() {
        return blackUpdateDate;
    }

    public void setBlackUpdateDate(ZonedDateTime blackUpdateDate) {
        this.blackUpdateDate = blackUpdateDate;
    }

    public ZonedDateTime getBlackSentencePeriod() {
        return blackSentencePeriod;
    }

    public void setBlackSentencePeriod(ZonedDateTime blackSentencePeriod) {
        this.blackSentencePeriod = blackSentencePeriod;
    }

    @Override
    public String toString() {
        return "BlackDTO{" +
                "blackNo=" + blackNo +
                ", userNo=" + userNo +
                ", auth='" + auth + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", blackReason='" + blackReason + '\'' +
                ", blackRegistDate=" + blackRegistDate +
                ", blackStatus='" + blackStatus + '\'' +
                ", blackUpdateDate=" + blackUpdateDate +
                ", blackSentencePeriod=" + blackSentencePeriod +
                '}';
    }
}
